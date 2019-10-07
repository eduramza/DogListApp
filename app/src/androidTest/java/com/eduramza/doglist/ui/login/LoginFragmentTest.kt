package com.eduramza.doglist.ui.login

import android.widget.BaseAdapter
import android.widget.GridView
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.eduramza.api.ABOUT_DEV
import com.eduramza.api.ERROR_EMAIL_IS_EMPTY_RESPONSE
import com.eduramza.api.ERROR_INVALID_EMAIL_RESPONSE
import com.eduramza.doglist.R
import org.hamcrest.Matchers
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@LargeTest
class LoginFragmentTest{

    @get:Rule
    var activityRule: ActivityTestRule<LoginActivity>
            = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun testEveryComponentsIsDisplayed(){
        onView(withId(R.id.edit_email)).check(matches(isDisplayed()))
        onView(withId(R.id.im_logo)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_login)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_about_dev)).check(matches(isDisplayed()))
        onView(withId(R.id.edit_email)).check(matches(withHint("e-mail")))
    }

    @Test
    fun testEmptyEmailError(){
        onView(withId(R.id.btn_login)).perform(click())
        val error = ERROR_EMAIL_IS_EMPTY_RESPONSE
        onView(withText(error)).check(matches(isDisplayed()))
    }

    @Test
    fun testInvalidEmail(){
        onView(withId(R.id.edit_email)).perform(typeText("raosadw"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.btn_login)).perform(click())

        val error = ERROR_INVALID_EMAIL_RESPONSE
        onView(withText(error)).check(matches(isDisplayed()))
    }

    @Test
    fun clickAboutDev(){
        onView(withId(R.id.tv_about_dev)).perform(click())

        val response = ABOUT_DEV
        onView(withText(response)).check(matches(isDisplayed()))
    }

    @Test
    fun testCorrectEmail(){
        onView(withId(R.id.edit_email)).perform(typeText("ramza@gmail.com"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.btn_login)).perform(click())

        //second activity
        onView(withId(R.id.menu_logout)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_logout)).perform(click())
    }

}