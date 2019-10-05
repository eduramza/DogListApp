package com.eduramza.api.repository.feed

import com.eduramza.api.source.IdWallApi
import com.eduramza.local.model.UserPreferences

class FeedRepositoryImpl(private val idWallApi: IdWallApi,
                         private val prefs: UserPreferences
                         ) : FeedRepository{

}