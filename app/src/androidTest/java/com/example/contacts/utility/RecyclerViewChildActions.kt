package com.example.contacts.utility

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.util.HumanReadables
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class RecyclerViewChildActions {

    companion object {

        /**
         * Based on github.com/xabaras/recyclerview-child-actions.
         * Checks that the childMatcher matches a child view
         * inside a RecyclerView's last item.
         */
        fun childAtLastPositionWithMatcher(
            childId: Int,
            childMatcher: Matcher<View>
        ): Matcher<View> {
            return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
                override fun describeTo(description: Description?) {
                    description?.appendText(
                        "Checks that the childMatcher matches a child view " +
                                " inside a RecyclerView's last item"
                    )
                }

                override fun matchesSafely(recyclerView: RecyclerView?): Boolean {
                    val viewHolder =
                        recyclerView?.findViewHolderForAdapterPosition(recyclerView.adapter!!.itemCount - 1)
                    val matcher = ViewMatchers.hasDescendant(
                        CoreMatchers.allOf(
                            ViewMatchers.withId(childId),
                            childMatcher
                        )
                    )
                    return viewHolder != null && matcher.matches(viewHolder.itemView)
                }
            }
        }

        /**
         * Based on github.com/xabaras/recyclerview-child-actions and RecyclerViewActions class.
         * Perform action on the child view inside a RecyclerView's last item.
         */
        fun <VH : RecyclerView.ViewHolder?> actionOnChildOfLastItem(
            action: ViewAction,
            childId: Int
        ): ViewAction {
            return object : ViewAction {
                override fun getDescription(): String {
                    return "Perform action on the child view inside a RecyclerView's last item"
                }

                override fun getConstraints(): Matcher<View> {
                    return allOf(isDisplayed(), isAssignableFrom(View::class.java))
                }

                override fun perform(uiController: UiController, view: View) {
                    val recyclerView = view as RecyclerView
                    val lastPosition = recyclerView.adapter!!.itemCount - 1

                    @Suppress("UNCHECKED_CAST")
                    val viewHolderForPosition =
                        recyclerView.findViewHolderForAdapterPosition(lastPosition) as VH
                            ?: throw PerformException.Builder()
                                .withActionDescription(this.toString())
                                .withViewDescription(HumanReadables.describe(view))
                                .withCause(IllegalStateException("No view holder at position: " + lastPosition))
                                .build()

                    val viewAtPosition = viewHolderForPosition.itemView
                    viewAtPosition.let {
                        val child: View = it.findViewById(childId)
                        action.perform(uiController, child)
                    }
                }
            }
        }
    }
}