package com.vivek.testing1.netwrok

class StateData<T> {

    var status: DataStatus? = null
        private set
    var data: T? = null
        private set
    var error: Throwable? = null
        private set

    fun loading(): StateData<T> {
        status = DataStatus.LOADING
        data = null
        error = null
        return this
    }

    fun success(data: T): StateData<T> {
        status = DataStatus.SUCCESS
        this.data = data
        error = null
        return this
    }

    fun error(error: Throwable): StateData<T> {
        status = DataStatus.ERROR
        data = null
        this.error = error
        return this
    }

    enum class DataStatus {
        SUCCESS, ERROR, LOADING
    }
}