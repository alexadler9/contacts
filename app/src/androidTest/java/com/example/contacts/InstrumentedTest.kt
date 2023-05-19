package com.example.contacts

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.contacts.domain.ContactModel
import com.example.contacts.feature.mainscreen.ui.MainActivity
import com.example.contacts.utility.RecyclerViewChildActions.Companion.actionOnChildOfLastItem
import com.example.contacts.utility.RecyclerViewChildActions.Companion.childAtLastPositionWithMatcher
import org.hamcrest.core.AllOf.allOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class InstrumentedTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun addContact_isSuccess() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        val contact = ContactModel(
            id = "",
            name = "name_${UUID.randomUUID()}",
            surname = "surname_${UUID.randomUUID()}",
            phone = "79999999999"
        )

        /**
         * Checking adding a contact.
         */

        onView(withId(R.id.fabAddContact))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.etName))
            .check(matches(isDisplayed()))
            .perform(typeText(contact.name))

        onView(withId(R.id.etSurname))
            .check(matches(isDisplayed()))
            .perform(typeText(contact.surname))

        onView(withId(R.id.etPhone))
            .check(matches(isDisplayed()))
            .perform(typeText(contact.phone))

        onView(withId(R.id.btnAdd))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.rvContacts))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.scrollToLastPosition<RecyclerView.ViewHolder>())
            .check(
                matches(
                    allOf(
                        childAtLastPositionWithMatcher(
                            R.id.tvName,
                            withText(
                                context.getString(
                                    R.string.contact_name,
                                    contact.surname,
                                    contact.name
                                )
                            )
                        ),
                        childAtLastPositionWithMatcher(
                            R.id.tvPhone,
                            withText(contact.phone)
                        )
                    )
                )
            )

        /**
         * Checking editing a contact.
         */

        val editedContact = contact.copy(name = "new_name_${UUID.randomUUID()}")

        onView(withId(R.id.rvContacts))
            .perform(RecyclerViewActions.scrollToLastPosition<RecyclerView.ViewHolder>())
            .perform(actionOnChildOfLastItem<RecyclerView.ViewHolder>(click(), R.id.ivMenu))

        onView(withText(context.getString(R.string.menu_contact_item_edit)))
            .inRoot(isPlatformPopup()).perform(click())

        onView(withId(R.id.etName))
            .check(matches(isDisplayed()))
            .check(matches(withText(contact.name)))
            .perform(clearText())
            .perform(typeText(editedContact.name))

        onView(withId(R.id.etSurname))
            .check(matches(isDisplayed()))
            .check(matches(withText(contact.surname)))

        onView(withId(R.id.etPhone))
            .check(matches(isDisplayed()))
            .check(matches(withText(contact.phone)))

        onView(withId(R.id.btnEdit))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.rvContacts))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.scrollToLastPosition<RecyclerView.ViewHolder>())
            .check(
                matches(
                    allOf(
                        childAtLastPositionWithMatcher(
                            R.id.tvName,
                            withText(
                                context.getString(
                                    R.string.contact_name,
                                    editedContact.surname,
                                    editedContact.name
                                )
                            )
                        ),
                        childAtLastPositionWithMatcher(
                            R.id.tvPhone,
                            withText(editedContact.phone)
                        )
                    )
                )
            )

        /**
         * Checking deleting a contact.
         */

        onView(withId(R.id.rvContacts))
            .perform(RecyclerViewActions.scrollToLastPosition<RecyclerView.ViewHolder>())
            .perform(actionOnChildOfLastItem<RecyclerView.ViewHolder>(click(), R.id.ivMenu))

        onView(withText(context.getString(R.string.menu_contact_item_delete)))
            .inRoot(isPlatformPopup()).perform(click())
    }
}