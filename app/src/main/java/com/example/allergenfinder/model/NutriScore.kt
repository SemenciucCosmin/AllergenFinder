package com.example.allergenfinder.model

import com.example.allergenfinder.R

enum class NutriScore(val grade: String, val imageRes: Int) {
    A("a", R.drawable.ic_nutri_score_a),
    B("b", R.drawable.ic_nutri_score_b),
    C("c", R.drawable.ic_nutri_score_c),
    D("d", R.drawable.ic_nutri_score_d),
    E("e", R.drawable.ic_nutri_score_e);

    companion object {
        fun getNutriScoreByGrade(grade: String?): NutriScore {
            return values().firstOrNull { it.grade == grade } ?: E
        }
    }
}
