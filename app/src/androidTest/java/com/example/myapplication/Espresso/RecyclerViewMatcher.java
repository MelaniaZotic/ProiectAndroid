package com.example.myapplication.Espresso;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;

import com.example.myapplication.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class RecyclerViewMatcher {
    private final int recyclerViewId;

    public RecyclerViewMatcher(int recyclerViewId) {
        this.recyclerViewId = recyclerViewId;
    }

    public Matcher<View> withItemText(final String text) {
        return withItemText(org.hamcrest.Matchers.is(text));
    }

    public static Matcher<View> withItemText(final Matcher<String> textMatcher) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item with text: ");
                textMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(RecyclerView view) {
                RecyclerView.Adapter<?> adapter = view.getAdapter();
                Resources resources = view.getResources();
                for (int position = 0; position < adapter.getItemCount(); position++) {
                    RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                    if (viewHolder == null) {
                        // This might occur if the RecyclerView is not fully laid out and displayed.
                        continue;
                    }
                    TextView textView = viewHolder.itemView.findViewById(R.id.recTitle); // Customize this ID based on your layout
                    if (textView != null && textMatcher.matches(textView.getText().toString())) {
                        return true;
                    }
                }
                return false;
            }
        };
    }
}
