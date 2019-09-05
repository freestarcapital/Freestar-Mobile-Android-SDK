package com.freestar.android.examples.freestarapplication2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.freestar.android.examples.freestarapplication2.dummy.DummyContent;
import com.freestar.android.sdk.domain.AdContentItem;
import com.freestar.android.sdk.domain.ContentItem;
import com.freestar.android.sdk.model.FreestarAdModel;
import com.freestar.android.sdk.model.FreestarRecyclerViewInjector;
import com.freestar.android.sdk.widget.holder.ItemViewHolder;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.prebid.fs.mobile.domain.CustomTargetingEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    private static final String AD_PLACEMENT_1 = "adPlacement1";
    private static final String AD_PLACEMENT_2 = "adPlacement2";
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        List<ContentItem> items = new ArrayList<>();
        items.addAll(DummyContent.ITEMS);
        FreestarRecyclerViewInjector injector = FreestarAdModel.getInstance(this).lookupRecyclerViewInjector(R.layout.item_list);
        String adSlot1 = FreestarAdModel.getInstance(this).getProperty(AD_PLACEMENT_2);
        List<ContentItem> masterItems = injector.injectBannerAd(items, "item_list", adSlot1);
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, masterItems, mTwoPane));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<ItemViewHolder> {

        private final ItemListActivity mParentActivity;
        private final List<ContentItem> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object item = view.getTag();
                if (item instanceof DummyContent.DummyItem) {
                    DummyContent.DummyItem dItem = (DummyContent.DummyItem) item;
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(ItemDetailFragment.ARG_ITEM_ID, dItem.id);
                        ItemDetailFragment fragment = new ItemDetailFragment();
                        fragment.setArguments(arguments);
                        mParentActivity.getSupportFragmentManager().beginTransaction()
                                .replace(R.id.item_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = view.getContext();
                        Intent intent = new Intent(context, ItemDetailActivity.class);
                        intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, dItem.id);

                        context.startActivity(intent);
                    }
                }
            }
        };

        SimpleItemRecyclerViewAdapter(ItemListActivity parent,
                                      List<ContentItem> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            FreestarRecyclerViewInjector injector = FreestarAdModel.getInstance(mParentActivity).lookupRecyclerViewInjector(R.layout.item_list);
            ItemViewHolder result = injector.getViewHolder(parent, viewType);
            if (result == null) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_content, parent, false);
                result = new DummyViewHolder(view);
            } else {
                PublisherAdView v = (PublisherAdView) ((LinearLayout)result.getInitView()).getChildAt(0);
                System.out.println("Frogs: "+v.getAdListener().getClass().getName());
            }
            return result;
        }

        @Override
        public void onBindViewHolder(final ItemViewHolder holder, int position) {
            ContentItem item = mValues.get(position);
            if (item instanceof DummyContent.DummyItem) {
                DummyContent.DummyItem itemD = (DummyContent.DummyItem) item;
                DummyViewHolder viewHolder = (DummyViewHolder) holder;
                viewHolder.mIdView.setText(itemD.id);
                viewHolder.mContentView.setText(itemD.content);
                holder.itemView.setTag(mValues.get(position));
                holder.itemView.setOnClickListener(mOnClickListener);
            } else {
                AdContentItem adItem = (AdContentItem) item;
                FreestarRecyclerViewInjector injector = FreestarAdModel.getInstance(mParentActivity).lookupRecyclerViewInjector(R.layout.item_list);
                injector.setAdListener(holder, new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        // do something
                    }

                    public void onAdClicked() {
                        // do something
                    }
                });

                //injector.process(holder, adItem);

                injector.process(holder, adItem,
                        new CustomTargetingEntry.ListBuilder()
                                .addCustomTargeting("pos", "top")
                                .addCustomTargeting("s1", "home")
                                .addCustomTargeting("pid", "home")
                                .build());
            }
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        @Override
        public int getItemViewType(int position) {
            ContentItem item = mValues.get(position);
            FreestarRecyclerViewInjector injector = FreestarAdModel.getInstance(mParentActivity).lookupRecyclerViewInjector(R.layout.item_list);
            return injector.getItemViewType(item);
        }

        class DummyViewHolder extends ItemViewHolder {
            final TextView mIdView;
            final TextView mContentView;

            DummyViewHolder(View view) {
                super(view);
                mIdView = view.findViewById(R.id.id_text);
                mContentView = view.findViewById(R.id.content);
            }
        }

    }
}
