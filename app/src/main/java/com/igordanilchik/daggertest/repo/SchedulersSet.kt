package com.igordanilchik.daggertest.repo

import rx.Scheduler
import rx.schedulers.Schedulers

/**
 * @author Igor Danilchik
 */
data class SchedulersSet(
        val ioScheduler: Scheduler,
        val computationScheduler: Scheduler,
        val uiScheduler: Scheduler,
        val immediateScheduler: Scheduler
) {

    companion object {

        @JvmStatic
        fun test(): SchedulersSet = from(Schedulers.immediate())

        fun from(scheduler: Scheduler): SchedulersSet =
                SchedulersSet(
                        scheduler,
                        scheduler,
                        scheduler,
                        scheduler
                )

    }

}