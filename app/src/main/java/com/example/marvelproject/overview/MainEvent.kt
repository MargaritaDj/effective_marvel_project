package com.example.marvelproject.overview

interface MainEvent

class GetHeroByIdEvent(val id: String): MainEvent
class GetHeroesEvent: MainEvent

