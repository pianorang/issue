package me.pianorang.issueservice.web

import me.pianorang.issueservice.config.AuthUser
import me.pianorang.issueservice.domain.IssueRepository
import me.pianorang.issueservice.domain.enums.IssueStatus
import me.pianorang.issueservice.model.IssueRequest
import me.pianorang.issueservice.service.IssueService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/issues")
class IssueController(private val issueService: IssueService) {
    @PostMapping
    fun create(authUser:AuthUser, @RequestBody request: IssueRequest) = issueService.create(authUser.userId, request)

    @GetMapping
    fun getAll(authUser: AuthUser,
               @RequestParam(required = false, defaultValue = "TODO") status:IssueStatus,
    ) = issueService.getAll(status)

    @GetMapping("/{id}")
    fun get( @PathVariable id: Long,) = issueService.get(id)

    @PutMapping("/{id}")
    fun edit(authUser: AuthUser, @PathVariable id: Long, @RequestBody request: IssueRequest,) =
        issueService.edit(authUser.userId, id, request)
}