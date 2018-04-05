package com.promact.youtubedemoapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by grishma on 09-03-2017.
 */
public class CategoryChannelsResponse implements Parcelable {
    private String kind;
    private String etag;
    private String nextPageToken;
    private PageInfo pageInfo;

    protected CategoryChannelsResponse(Parcel in) {
        kind = in.readString();
        etag = in.readString();
        nextPageToken = in.readString();
    }

    public static final Creator<CategoryChannelsResponse> CREATOR = new Creator<CategoryChannelsResponse>() {
        @Override
        public CategoryChannelsResponse createFromParcel(Parcel in) {
            return new CategoryChannelsResponse(in);
        }

        @Override
        public CategoryChannelsResponse[] newArray(int size) {
            return new CategoryChannelsResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(kind);
        parcel.writeString(etag);
        parcel.writeString(nextPageToken);
    }

    public class PageInfo
    {
        private int totalResults;
        private int resultsPerPage;

        public int getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }

        public int getResultsPerPage() {
            return resultsPerPage;
        }

        public void setResultsPerPage(int resultsPerPage) {
            this.resultsPerPage = resultsPerPage;
        }
    }
    private ArrayList<Items1>items;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public ArrayList<Items1> getItems() {
        return items;
    }

    public void setItems(ArrayList<Items1> items) {
        this.items = items;
    }

    public class Items1 implements Parcelable {
        private String kind;
        private String etag;
        private String id;
        private Snippet1 snippet;
        private Localized localized;

        protected Items1(Parcel in) {
            kind = in.readString();
            etag = in.readString();
            id = in.readString();
        }

        public final Creator<Items1> CREATOR = new Creator<Items1>() {
            @Override
            public Items1 createFromParcel(Parcel in) {
                return new Items1(in);
            }

            @Override
            public Items1[] newArray(int size) {
                return new Items1[size];
            }
        };

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public String getEtag() {
            return etag;
        }

        public void setEtag(String etag) {
            this.etag = etag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Snippet1 getSnippet() {
            return snippet;
        }

        public void setSnippet(Snippet1 snippet) {
            this.snippet = snippet;
        }

        public Localized getLocalized() {
            return localized;
        }

        public void setLocalized(Localized localized) {
            this.localized = localized;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(kind);
            parcel.writeString(etag);
            parcel.writeString(id);
        }

        public class Localized
        {
            private String title;
            private String description;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
        public class Snippet1
        {
            private String title;
            private String description;
            private String publishedAt;
            private Thumbnails thumbnails;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public Thumbnails getThumbnails() {
                return thumbnails;
            }

            public void setThumbnails(Thumbnails thumbnails) {
                this.thumbnails = thumbnails;
            }

            public class Thumbnails
            {
                private Medium medium;

                public Medium getMedium() {
                    return medium;
                }

                public void setMedium(Medium medium) {
                    this.medium = medium;
                }

                public class Medium
                {
                    private String url;

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }
                }
            }
        }
    }
}
