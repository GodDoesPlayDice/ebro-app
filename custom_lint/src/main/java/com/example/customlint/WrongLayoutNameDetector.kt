package com.example.customlint

import com.android.tools.lint.detector.api.Category.Companion.CORRECTNESS
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.LayoutDetector
import com.android.tools.lint.detector.api.Project
import com.android.tools.lint.detector.api.Scope.Companion.RESOURCE_FILE_SCOPE
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.XmlContext
import org.w3c.dom.Document

@Suppress("UnstableApiUsage")
class WrongLayoutNameDetector : LayoutDetector() {

    override fun visitDocument(context: XmlContext, document: Document) {
        val modified = allowedPrefixes.map {
            val resourcePrefix = context.project.resourcePrefix().forceUnderscoreIfNeeded()
            if (resourcePrefix != it) resourcePrefix + it else it
        }
        val doesNotStartWithPrefix = modified.none { context.file.name.startsWith(it) }
        val notEquals = modified.map { it.dropLast(1) }.none { context.file.name == "$it.xml" }
        if (doesNotStartWithPrefix && notEquals) {
            val message =
                "Layout does not start with one of the following prefixes: ${modified.joinToString()}"
            context.report(ISSUE, document, context.getLocation(document), message)
        }
    }

    private fun String.forceUnderscoreIfNeeded() =
        if (isNotEmpty() && !endsWith("_")) plus("_") else this

    private fun Project.resourcePrefix() = buildModule?.resourcePrefix.orEmpty()

    companion object {
        private val allowedPrefixes = listOf(
            "activity_",
            "view_",
            "fragment_",
            "dialog_",
            "list_item_",
            "menu_item_",
            "custom_layout_"
        )

        val ISSUE = Issue.create(
            "WrongLayoutName",
            "Layout names should be prefixed accordingly.",
            "The layout file name should be prefixed with one of the following: ${allowedPrefixes.joinToString()}.",
            CORRECTNESS,
            5,
            Severity.ERROR,
            Implementation(WrongLayoutNameDetector::class.java, RESOURCE_FILE_SCOPE)
        )
    }
}
