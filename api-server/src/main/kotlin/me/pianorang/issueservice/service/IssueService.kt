package me.pianorang.issueservice.service

import me.pianorang.issueservice.domain.Issue
import me.pianorang.issueservice.domain.IssueRepository
import me.pianorang.issueservice.domain.enums.IssueStatus
import me.pianorang.issueservice.exception.NotFoundException
import me.pianorang.issueservice.model.IssueRequest
import me.pianorang.issueservice.model.IssueResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class IssueService(
    private val issueRepository: IssueRepository,
) {
    @Transactional
    fun create(userId: Long, request: IssueRequest): IssueResponse{
        val issue = Issue(
            summary = request.summary,
            description = request.description,
            userId = userId,
            type= request.type,
            priority = request.priority,
            status = request.status
        )
        return IssueResponse(issueRepository.save(issue))
    }

    @Transactional(readOnly = true)
    fun getAll(status: IssueStatus) =
        issueRepository.findAllByStatusOrderByCreatedAtDesc(status)?.map { IssueResponse(it)  }

    @Transactional(readOnly = true)
    fun get(id: Long): IssueResponse = IssueResponse(
        issueRepository.findByIdOrNull(id) ?: throw NotFoundException("이슈가 존재하지 않습니다.")
    )

    @Transactional
    fun edit(userId: Long, id: Long, request: IssueRequest): IssueResponse {
        val issue = issueRepository.findByIdOrNull(id) ?: throw NotFoundException("이슈가 존재하지 않습니다.")
        return with(issue){
            summary = request.summary
            description = request.description
            this.userId = userId
            type = request.type
            priority = request.priority
            status = request.status
            IssueResponse(issueRepository.save(this))
        }

    }
}