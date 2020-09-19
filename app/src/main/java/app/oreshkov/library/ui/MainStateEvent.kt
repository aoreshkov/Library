package app.oreshkov.library.ui

sealed class MainStateEvent{
    object GetBooksEvent: MainStateEvent()
    object None: MainStateEvent()
}