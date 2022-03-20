package ru.kkuzmichev.simpleappforespresso;

import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;

class NewMatcher {

    public static Matcher<View> withListSize(final int size) {
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(final View view) {
                return ((RecyclerView) view).getAdapter().getItemCount() == size;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText(size + " items");
            }
        };
    }

    public static Matcher<View> atPositionItemVisible(final int position) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("Item at" + position + ": ");
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    return false;
                }
                return viewHolder.itemView.getVisibility() == View.VISIBLE;
            }
        };
    }
}
