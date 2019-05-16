package com.sinigr.usersapp.base

import kotlinx.coroutines.Job

interface ICoroutineInteractor {

    var jobs: ArrayList<Job>

    fun addJob(job: Job) {
        jobs.add(job)
    }

    fun stopWork() {
        clearJobQueue()
    }

    private fun clearJobQueue() {
        jobs.forEach { it.cancel() }
        jobs.clear()
    }
}