package com.promact.youtubedemoapp;
// Created by grishma on 17-03-2017.


/*
public class DataAdapter extends RecyclerView.Adapter {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private ImageView iconCatChannel;
    private TextView catTitle;
    private Context context;
    private ArrayList<ChannelsItemResponse.Items> channelsIemsList;

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;



    public DataAdapter(Context context, ArrayList<ChannelsItemResponse.Items> channelsIemsList, RecyclerView recyclerView) {
        this.channelsIemsList = channelsIemsList;
        this.context=context;



        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {

            final GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView
                    .getLayoutManager();


            recyclerView
                    .addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView,
                                               int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);

                            totalItemCount = gridLayoutManager.getItemCount();
                            lastVisibleItem = gridLayoutManager
                                    .findLastVisibleItemPosition();
                            if (!loading
                                    && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                                // End has been reached
                                // Do something
                                if (onLoadMoreListener != null) {
                                    onLoadMoreListener.onLoadMore();
                                }
                                loading = true;
                            }
                        }
                    });
        }
        return;
    }

    @Override
    public int getItemViewType(int position) {
        return channelsIemsList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_grid, parent, false);

            vh = new StudentViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.loading_view, parent, false);

            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StudentViewHolder) {
            ((StudentViewHolder) holder).catTitle.setText( channelsIemsList.get(position).getSnippet().getTitle()); // Setting the Text with the array of our Titles
           // ((StudentViewHolder) holder).iconCatChannel.setImageResource(position);// Settimg the image with array of our icons
            Picasso.with(context).load(channelsIemsList.get(position).getSnippet().getThumbnails().getHigh().getUrl()).into(((StudentViewHolder) holder).iconCatChannel);
               */
/* if (catTitle.getText().equals("")) {
                    catTitle.setVisibility(View.GONE);
                } else {
                    catTitle.setVisibility(View.VISIBLE);
                }*//*



        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemCount() {
        return channelsIemsList.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }


    //
    public static class StudentViewHolder extends RecyclerView.ViewHolder {

        public TextView catTitle;

        public ImageView iconCatChannel;

        public ChannelsItemResponse.Items student;

        public StudentViewHolder(View v) {
            super(v);
            iconCatChannel = (ImageView) v.findViewById(R.id.image);
            catTitle = (TextView) v.findViewById(R.id.text);


            v.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),
                            "OnClick :" + student.getSnippet().getTitle() + " \n "+student.getSnippet().getThumbnails().getHigh().getUrl(),
                            Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressbar);
        }
    }
}
*/
