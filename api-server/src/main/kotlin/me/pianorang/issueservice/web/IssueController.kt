package me.pianorang.issueservice.web

import me.pianorang.issueservice.config.AuthUser
import me.pianorang.issueservice.domain.IssueRepository
import me.pianorang.issueservice.model.IssueRequest
import me.pianorang.issueservice.service.IssueService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/issues")
class IssueController(private val issueService: IssueService) {
    @PostMapping
    fun create(authUser:AuthUser, @RequestBody request: IssueRequest) = issueService.create(authUser.userId, request)
}