package com.androstays.a7minuteworkout

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel> {
        val exerciseList: ArrayList<ExerciseModel>
        val jumpingJacks =
            ExerciseModel(
                1, "Jumping Jacks", R.drawable.ic_jumping_jacks,
                isCompleted = false,
                isSelected = false
            )
        val wallSit =
            ExerciseModel(
                2, "Wall Sit", R.drawable.ic_wall_sit,
                isCompleted = false,
                isSelected = false
            )
        val pushUp = ExerciseModel(
            3, "Push Up", R.drawable.ic_push_up, isCompleted = false,
            isSelected = false
        )
        val abdominalCrunch =
            ExerciseModel(
                4, "Abdominal Crunch", R.drawable.ic_abdominal_crunch, isCompleted = false,
                isSelected = false
            )
        val stepUpOnToChair =
            ExerciseModel(
                5, "Step on to chair", R.drawable.ic_step_up_onto_chair, isCompleted = false,
                isSelected = false
            )
        val squat =
            ExerciseModel(
                6, "Step on to chair", R.drawable.ic_squat, isCompleted = false,
                isSelected = false
            )
        val tricepsDripOnChair = ExerciseModel(
            7,
            "Triceps dip on chair",
            R.drawable.ic_triceps_dip_on_chair,
            isCompleted = false,
            isSelected = false
        )
        val plank = ExerciseModel(
            8, "Plank", R.drawable.ic_plank, isCompleted = false,
            isSelected = false
        )
        val highKneesRunningInPlace = ExerciseModel(
            9,
            "High Knees Running In Place",
            R.drawable.ic_high_knees_running_in_place,
            isCompleted = false,
            isSelected = false
        )
        val lunges =
            ExerciseModel(
                10, "Jumping Jacks", R.drawable.ic_lunge, isCompleted = false,
                isSelected = false
            )
        val pushUpAndRotation = ExerciseModel(
            11,
            "PushUp and Rotation",
            R.drawable.ic_push_up_and_rotation,
            isCompleted = false,
            isSelected = false
        )
        exerciseList = arrayListOf(
            jumpingJacks,
            wallSit,
            pushUp,
            abdominalCrunch,
            stepUpOnToChair,
            squat,
            tricepsDripOnChair,
            plank,
            highKneesRunningInPlace,
            lunges,
            pushUpAndRotation
        )
        return exerciseList

    }
}