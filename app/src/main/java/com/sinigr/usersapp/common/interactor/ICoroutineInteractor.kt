package com.sinigr.usersapp.common.interactor

import kotlinx.coroutines.Job

interface ICoroutineInteractor {

  var jobs: MutableSet<Job>

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