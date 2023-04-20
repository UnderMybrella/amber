package dev.brella.amber.plugin.settings

import dev.brella.amber.kotlin.KotlinCodeGenSettings
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.property
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.setValue
import javax.inject.Inject

open class KotlinCodeGenSettingsGradle @Inject constructor(factory: ObjectFactory) : KotlinCodeGenSettings.Mutable {
    val indentProperty: Property<String> = factory.property()
    val autoSpacerProperty: Property<String?> = factory.property()
    val autoSeparatorProperty: Property<String?> = factory.property()

    override var indent: String by indentProperty
    override var autoSpacer: String? by autoSpacerProperty
    override var autoSeparator: String? by autoSeparatorProperty

    public infix fun convention(other: KotlinCodeGenSettingsGradle?): KotlinCodeGenSettingsGradle {
        if (other == null) return this

        indentProperty.convention(other.indentProperty)
        autoSpacerProperty.convention(other.autoSpacerProperty)
        autoSeparatorProperty.convention(other.autoSeparatorProperty)

        return this
    }

    init {
        indentProperty.convention(KotlinCodeGenSettings.indent)
        autoSpacerProperty.convention(KotlinCodeGenSettings.autoSpacer)
        autoSeparatorProperty.convention(KotlinCodeGenSettings.autoSeparator)
    }
}