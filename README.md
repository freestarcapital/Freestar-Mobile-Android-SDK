![Freestar](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/raw/master/images/freestar.jpg)
# Freestar Mobile Android SDK Integration Guide
# API - _freestar-android-sdk_ Recycler View Injector

### What's New
We are pleased to announce the release of our SDK! Banner ad formats are currently supported, with more coming.  Be sure to check-in frequently for the latest releases and announcements.

###### Change History
|  Version  |     Release Date     |                Description                |
| --------- | -------------------- | ----------------------------------------- |
| __1.0.0__ | _October 14th, 2019_ |  • Initial release.                       |

###### GMA SDK Compatibility Matrix

| FSAdSDK Version | GMS play-services-ads Version | Repository |
| --------------- | ----------------------------- | ---------- |
| _____1.0.0_____ | ___________18.2.0____________ |  jcenter() |

---
#### Minimum Requirements
minSDKVersion 16
targetSDKVersion 28
com.android.tools.build:gradle 3.5.1

## Getting Started
---

Here are the basic steps required to use the injector your project.

`1. ` Edit your _assets/freestar_ads.properties_ file and add in your ad specific information (for example it should look something like this)

Option A
```
#SHARE_GEO_LOCATION=true

PREBID_FSDATA_ID=com.freestar.android.examples
#USE_PREBID_DEV_HOST=true

adPlacement1=freestar_androidapp_320x50_ATF
adPlacement2=freestar_androidapp_300x250_InContent
dfpPlacement1=/15184186/freestar_androidapp_320x50_ATF
dfpSize1=320x50
dfpPlacement2=/15184186/freestar_androidapp_300x250_InContent
dfpSize2=300x250
ad_placement1.autoRefreshSeconds=30
ad_placement2.autoRefreshSeconds=30

# the number of items between ad injections
item_list.listInjectOffsetCount=3

# should the list have a leading ad?
item_list.listInjectOffsetIncludeLeading=true

# should the list have a mandated trailing ad?
item_list.listInjectOffsetIncludeTrailing=true

# ad refresh rate override
#item_list.listInjectAutoRefresh=120000

```

Option B (preferred)
```
#SHARE_GEO_LOCATION=true

PREBID_FSDATA_ID=com.freestar.android.examples
#USE_PREBID_DEV_HOST=true

```

`2. ` Modify the domain object that is to be listed.  On our case the object id is of type **String** so we will need to extends **com.freestar.android.sdk.domain.StringContentItem** and implement the methods in its interface.

before
```
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
    
```

after
```
import com.freestar.android.sdk.domain.StringContentItem;
...
    public static class DummyItem extends StringContentItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public int compareTo(StringContentItem item) {
            return id.compareTo(item.getId());
        }

    }
```

`3. ` In your activity, modify the setupRecyclerView() method.

Create your adSlot objects.  They should be static within the activity to ensure consistent ad activity as activities are recreated, etc.  Choose the approprate slot type.  And choose your custom tags as needed.

Option A
```
    private static final String AD_PLACEMENT_1 = "adPlacement1";
    private static final String AD_PLACEMENT_2 = "adPlacement2";
    private static final String DFP_PLACEMENT_1 = "dfpPlacement1";
    private static final String DFP_PLACEMENT_2 = "dfpPlacement2";
    private static final String DFP_SIZE_1 = "dfpSize1";
    private static final String DFP_SIZE_2 = "dfpSize2";
```

before
```
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, mTwoPane));
```

after
```
        List<ContentItem> items = new ArrayList<>();
        items.addAll(DummyContent.ITEMS);
        FreestarRecyclerViewInjector injector = FreestarAdModel.getInstance(this).lookupRecyclerViewInjector(R.layout.activity_item_list);

        String adSlot1 = FreestarAdModel.getInstance(this).getProperty(AD_PLACEMENT_1);
        String adSlot2 = FreestarAdModel.getInstance(this).getProperty(AD_PLACEMENT_2);
        String dfpSlot1 = FreestarAdModel.getInstance(this).getProperty(DFP_PLACEMENT_1);
        String dfpSlot2 = FreestarAdModel.getInstance(this).getProperty(DFP_PLACEMENT_2);
        String dfpSize1 = FreestarAdModel.getInstance(this).getProperty(DFP_SIZE_1);
        String dfpSize2 = FreestarAdModel.getInstance(this).getProperty(DFP_SIZE_2);

        List<ContentItem> masterItems = injector.injectBannerAd(items, "item_list", adSlot1);
        //List<ContentItem> masterItems = injector.injectBannerAd(items, "item_list", dfpSlot1, dfpSize1);
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, masterItems, mTwoPane));
```

Option B (Preferred)
```
    private static final FreestarAdSlot adSlot = new FreestarAdSlot.Builder()
            .setPlacementId("freestar_androidapp_320x50_ATF")
            .addCustomTarget("pos", "top")
            .addCustomTarget("s1", "home")
            .addCustomTarget("pid", "home")
            .build();

or
    private static final DfpAdSlot adSlot = new DfpAdSlot.Builder()
            .setDfpPlacementId("/15184186/freestar_androidapp_320x50_ATF")
            .setAutoRefreshSeconds(30)
            .addSize(320, 50)
            .addCustomTarget("pos", "top")
            .addCustomTarget("s1", "home")
            .addCustomTarget("pid", "home")
            .build();
```

before
```
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, mTwoPane));
```

after
```
        List<ContentItem> items = new ArrayList<>();
        items.addAll(DummyContent.ITEMS);
        FreestarRecyclerViewInjector injector = FreestarAdModel.getInstance(this).lookupRecyclerViewInjector(adSlot);
        List<ContentItem> masterItems = injector.injectBannerAd(items, new ListInjectorProperties.Builder()
                                                                                       .setListInjectOffsetCount(3)
                                                                                       .setListInjectOffsetIncludeLeading(true)
                                                                                       .setListInjectOffsetIncludeTrailing(true)                 
                                                                                       .build(), adSlot);
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, masterItems, mTwoPane));
```

`4. ` In your activity, change the SimpleItemRecyclerViewAdapter.

before
```
    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.DummyViewHolder> {

        private final ItemListActivity mParentActivity;
        private final List<DummyContent.DummyItem> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.id);
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(ItemListActivity parent,
                                      List<DummyContent.DummyItem> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

```

after
```
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

```

`5. ` In your activity, continuing on the SimpleItemRecyclerViewAdapter with the onCreateViewHolder() method.

OPTION A
before
```
        public DummyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new DummyViewHolder(view);
        }
```

after
```
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            FreestarRecyclerViewInjector injector = FreestarAdModel.getInstance(mParentActivity).lookupRecyclerViewInjector(R.layout.activity_item_list);
            ItemViewHolder result = injector.getViewHolder(parent, viewType);
            if (result == null) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_content, parent, false);
                result = new DummyViewHolder(view);
            }
            return result;
        }
```
OPTION B (Preferred)
before
```
        public DummyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new DummyViewHolder(view);
        }
```

after
```
        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            FreestarRecyclerViewInjector injector = FreestarAdModel.getInstance(mParentActivity).lookupRecyclerViewInjector(adSlot);
            ItemViewHolder result = injector.getViewHolder(parent, viewType, adSlot);
            if (result == null) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_content, parent, false);
                result = new DummyViewHolder(view);
            }
            return result;
        }
```

`6. ` In your activity, continuing on the SimpleItemRecyclerViewAdapter with the onBindViewHolder() method.

Option A
before
```
        public void onBindViewHolder(final DummyViewHolder holder, int position) {
            holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).content);

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
```

after
```
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
                FreestarRecyclerViewInjector injector = FreestarAdModel.getInstance(mParentActivity).lookupRecyclerViewInjector(R.layout.activity_item_list);
                injector.setAdListener(holder, new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        // do something
                    }

                    public void onAdClicked() {
                        // do something
                    }
                });

                injector.process(holder, adItem,
                        new CustomTargetingEntry.ListBuilder()
                                .addCustomTargeting("pos", "top")
                                .addCustomTargeting("s1", "home")
                                .addCustomTargeting("pid", "home")
                                .build());
                //injector.process(holder, adItem);
            }
        }
```

Option B (Preferred)
before
```
        public void onBindViewHolder(final DummyViewHolder holder, int position) {
            holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).content);

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
```

after
```
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
                FreestarRecyclerViewInjector injector = FreestarAdModel.getInstance(mParentActivity).lookupRecyclerViewInjector(adSlot);
                injector.setAdListener(holder, new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        // do something
                    }

                    public void onAdClicked() {
                        // do something
                    }
                });

                injector.process(holder, adItem, adSlot);
            }
        }
```

`7. ` In your activity, continuing on the SimpleItemRecyclerViewAdapter add a getItemViewType() method.

Option A
```
        @Override
        public int getItemViewType(int position) {
            ContentItem item = mValues.get(position);
            FreestarRecyclerViewInjector injector = FreestarAdModel.getInstance(mParentActivity).lookupRecyclerViewInjector(R.layout.activity_item_list);
            return injector.getItemViewType(item);
        }
```
Option B (Preferred)
```
        @Override
        public int getItemViewType(int position) {
            ContentItem item = mValues.get(position);
            FreestarRecyclerViewInjector injector = FreestarAdModel.getInstance(mParentActivity).lookupRecyclerViewInjector(adSlot);
            return injector.getItemViewType(item);
        }
```


`8. ` In your activity, continuing on the SimpleItemRecyclerViewAdapter - change the DummyViewHolder.

before
```
        class DummyViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;

            DummyViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
            
            @Override
            public void resume() {

            }

            @Override
            public void pause() {

            }

            @Override
            public boolean isPaused() {
                return false;
            }

            @Override
            public void destroy() {

            }
        }
```

after
```
        class DummyViewHolder extends ItemViewHolder {
            final TextView mIdView;
            final TextView mContentView;

            DummyViewHolder(View view) {
                super(view);
                mIdView = view.findViewById(R.id.id_text);
                mContentView = view.findViewById(R.id.content);
            }

            @Override
            public void resume() {

            }

            @Override
            public void pause() {

            }

            @Override
            public boolean isPaused() {
                return false;
            }

            @Override
            public void destroy() {

            }
        }
```
`9. ` Add the following methods to your activity class. 

Option A
```
    @Override
    protected void onResume() {
        super.onResume();
        FreestarAdModel.getInstance(this).lookupRecyclerViewInjector(R.layout.activity_item_list).resumeBannerAds();
    }

    @Override
    protected void onPause() {
        FreestarAdModel.getInstance(this).lookupRecyclerViewInjector(R.layout.activity_item_list).pauseBannerAds();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        FreestarAdModel.getInstance(this).lookupRecyclerViewInjector(R.layout.activity_item_list).destroyBannerAds();
        FreestarAdModel.releaseInstance(this);
        super.onDestroy();
    }

```
Option B (Preferred)
```
    @Override
    protected void onResume() {
        super.onResume();
        FreestarAdModel.getInstance(this).lookupRecyclerViewInjector(adSlot).resumeBannerAds();
    }

    @Override
    protected void onPause() {
        FreestarAdModel.getInstance(this).lookupRecyclerViewInjector(adSlot).pauseBannerAds();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        FreestarAdModel.getInstance(this).lookupRecyclerViewInjector(adSlot).destroyBannerAds();
        super.onDestroy();
    }

```


## By Example

The **master** branch contains the basic reference RecyclerView application.  The **freestar-api-install** branch has the initialization steps completed, and may act as a reference application for further examples.

[**Reference Application**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/new/master2)

The basic reference application (#2) can be found here.  A very simple, RecyclerView app.

[**Install2 Freestar API Branch**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-api-install2)

Example of reference application (RecyclerView) (#2), with the **Freestar** ads api installed and ready for specific advertising options to be implemented with .  All advertising examples will be derived by the activities defined by this option (#2-I).

[**Using FreestarRecycler View Injector**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-recycler-view-injector)

Example of reference application (#2-I), with the usage of the **Freestar** recycler view injector.  This facility injects ads into an existing list, destined for a RecyclerView.
