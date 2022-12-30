package com.example.customlint

import com.android.SdkConstants.ATTR_ID
import com.android.SdkConstants.BUTTON
import com.android.SdkConstants.CHECK_BOX
import com.android.SdkConstants.EDIT_TEXT
import com.android.SdkConstants.FRAME_LAYOUT
import com.android.SdkConstants.IMAGE_VIEW
import com.android.SdkConstants.LINEAR_LAYOUT
import com.android.SdkConstants.LIST_VIEW
import com.android.SdkConstants.PROGRESS_BAR
import com.android.SdkConstants.RADIO_BUTTON
import com.android.SdkConstants.RADIO_GROUP
import com.android.SdkConstants.RELATIVE_LAYOUT
import com.android.SdkConstants.TEXT_VIEW
import com.android.SdkConstants.VIEW_ANIMATOR
import com.android.resources.ResourceUrl
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.LayoutDetector
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.XmlContext
import org.w3c.dom.Attr

@Suppress("UnstableApiUsage")
class WrongViewIdDetector : LayoutDetector() {

    override fun getApplicableAttributes(): Collection<String> = listOf(ATTR_ID)

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        var clazz: String = attribute.ownerElement.localName
            .replace("AppCompat", "")
            .replace("Material", "")
        if (clazz.contains(".")) {
            clazz = clazz.substring(clazz.lastIndexOf(".") + 1)
        }
        val id = ResourceUrl.parse(attribute.value)?.name ?: ""
        if (idsPrefixMap.contains(clazz)) {
            val incorrectId = !id.isLowerCamelCase() || !id.startsWith(idsPrefixMap.getValue(clazz))
            if (incorrectId) {
                val message = "Incorrect view's id. Look at project's README"
                context.report(ISSUE, attribute, context.getLocation(attribute), message)
            }
        }
    }

    private fun String.isLowerCamelCase() = !Character.isUpperCase(this[0]) && !contains("_")

    companion object {
        private val idsPrefixMap: Map<String, String> = mapOf(
            TEXT_VIEW to "tv",
            "RecyclerView" to "rv",
            BUTTON to "btn",
            EDIT_TEXT to "et",
            CHECK_BOX to "cb",
            RADIO_BUTTON to "rb",
            RADIO_GROUP to "rg",
            LINEAR_LAYOUT to "ll",
            RELATIVE_LAYOUT to "rl",
            FRAME_LAYOUT to "fl",
            LIST_VIEW to "lv",
            "ConstraintLayout" to "cl",
            IMAGE_VIEW to "img",
            "CardView" to "cv",
            VIEW_ANIMATOR to "va",
            PROGRESS_BAR to "pb"
        )

        val ISSUE = Issue.create(
            "WrongViewIdName",
            "View id names should be prefixed accordingly.",
            "Look at project's README on how to proper name view's id",
            Category.CORRECTNESS,
            5,
            Severity.ERROR,
            Implementation(WrongViewIdDetector::class.java, Scope.RESOURCE_FILE_SCOPE)
        )
    }
}
