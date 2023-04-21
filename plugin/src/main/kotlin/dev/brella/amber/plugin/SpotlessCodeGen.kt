package dev.brella.amber.plugin

public fun CodeGenerationContainer.runSpotlessApplyAfterGeneration() {
    val spotlessApply = tasks.findByPath("spotlessApply")
    if (spotlessApply != null) {
        configureTasks { finalizedBy(spotlessApply) }
    }
}