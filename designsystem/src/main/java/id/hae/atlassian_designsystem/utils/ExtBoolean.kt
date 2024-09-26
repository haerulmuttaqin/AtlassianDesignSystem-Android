package id.hae.atlassian_designsystem.utils

fun Boolean?.orFalse(): Boolean = this ?: false
fun Boolean.isFalse() = !this
fun Boolean?.isTrue() = this.orFalse()