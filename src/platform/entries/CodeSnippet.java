package platform.entries;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a code snippet with various attributes such as its unique ID, code,
 * header, creation date, views, deletion date (if temporary), and views limit.
 * Provides methods for checking if the snippet is restricted or available,
 * incrementing its view count, and modifying its properties. Uses JPA annotations
 * for persistence.
 */
@Entity
@Table
public class CodeSnippet {

    @Id
    @NotNull
    private final String snippetUUID = UUID.randomUUID().toString();

    @NotNull
    @Column(length = 65536)
    private String code = "";
    @NotNull
    private String header = "Untitled";
    @NotNull
    private LocalDateTime createDate = LocalDateTime.now();

    private int views = 0;

    @Nullable
    private LocalDateTime deleteDate = null;
    @Nullable
    Integer viewsLimit = null;

    //Default constructor for CodeSnippet
    public CodeSnippet() {
    }

    //Constructor for CodeSnippet that takes the code as input.
    //@param code The code snippet to be stored.
    public CodeSnippet(@NotNull String code) {
        this.code = code;
    }

    //Constructor for CodeSnippet that takes both the code and header as input.
    //@param code The code snippet to be stored.
    //@param header The title of the code snippet.
 * @pa
    public CodeSnippet(@NotNull String code, @NotNull String header) {
        this.code = code;
        this.header = header;
    }

    //Checks if the code snippet is restricted (i.e., has a deletion date and view limit set).
    //@return True if the snippet is restricted, false otherwise.
    public boolean checkIsRestricted() {
        return deleteDate != null && viewsLimit != null;
    }

    //Checks if the code snippet is still available (i.e., not deleted and not exceeding view limit).
    // @return True if the snippet is available, false otherwise.
    public boolean checkIsAvailable() {
        if (deleteDate != null && deleteDate.isBefore(LocalDateTime.now())) {
            return false;
        }
        return viewsLimit == null || views < viewsLimit;
    }

    //Increments the view count of the code snippet
    public void view() {
        views++;
    }

    //Returns the unique ID of the code snippet
    public @NotNull String getSnippetUUID() {
        return snippetUUID;
    }

    //Returns the code stored in the code snippet
    public @NotNull String getCode() {
        return code;
    }

    //Sets the code of the code snippet.
    public void setCode(@NotNull String code) {
        this.code = code;
    }

    //Returns the creation date of the code snippet
    public @NotNull LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(@NotNull LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public long getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public @Nullable LocalDateTime getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(@Nullable LocalDateTime deleteDate) {
        this.deleteDate = deleteDate;
    }

    public @Nullable Integer getViewsLimit() {
        return viewsLimit;
    }

    public void setViewsLimit(@Nullable Integer viewsLimit) {
        this.viewsLimit = viewsLimit;
    }

    public @NotNull String getHeader() {
        return header;
    }

    public void setHeader(@NotNull String header) {
        this.header = header;
    }

    //Checks if code snippets are equal, checks all seven factors
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeSnippet that = (CodeSnippet) o;
        return views == that.views && snippetUUID.equals(that.snippetUUID) && code.equals(that.code) && header.equals(that.header) && createDate.equals(that.createDate) && Objects.equals(deleteDate, that.deleteDate) && Objects.equals(viewsLimit, that.viewsLimit);
    }

    //returns a string representation of the codesnippet object
    @Override
    public String toString() {
        return "CodeSnippet{" +
                "snippetUUID='" + snippetUUID + '\'' +
                ", code='" + code + '\'' +
                ", header='" + header + '\'' +
                ", createDate=" + createDate +
                ", views=" + views +
                ", deleteDate=" + deleteDate +
                ", viewsLimit=" + viewsLimit +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(snippetUUID, code, header, createDate, views, deleteDate, viewsLimit);
    }
}