package id.ac.poliban.mi.adit.roomwordssampleupdate;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter for the RecyclerView that displays a list of words.
 */

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private List<Word> mWords; // Cached copy of words
	private static ClickListener clickListener;

    WordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mWords != null) {
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText(R.string.no_word);
        }
    }

    /**
     * Associates a list of words with this adapter
    */
    void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    /**
     * getItemCount() is called many times, and when it is first called,
     * mWords has not been updated (means initially, it's null, and we can't return null).
     */
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

    /**
     * Gets the word at a given position.
     * This method is useful for identifying which word
     * was clicked or swiped in methods that handle user events.
     *
     * @param position The position of the word in the RecyclerView
     * @return The word at the given position
     */
    public Word getWordAtPosition(int position) {
        return mWords.get(position);
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(view, getAdapterPosition());
                }
            });
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        WordListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View v, int position);
    }

}
