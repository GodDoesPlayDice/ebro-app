package com.example.customlint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

@Suppress("UnstableApiUsage")
class CustomIssueRegistry : IssueRegistry() {
    override val issues: List<Issue> = listOf(
        WrongViewIdDetector.ISSUE,
        WrongLayoutNameDetector.ISSUE
    )

    override val api: Int = CURRENT_API
}
