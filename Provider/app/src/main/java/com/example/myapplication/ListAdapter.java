package com.example.myapplication;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<Student> implements Filterable {

    int mResource;
    private Context context;
    private Filter filter;
    private SparseBooleanArray mSelectedItemsIds;
    boolean[] checkboxStates;
    Boolean c;
    List<Student> studentList = new ArrayList<>();
    List<Student> filteredStudents = new ArrayList<>();
    Boolean[] bool = new Boolean[20];

    List<Student> temp = new ArrayList<>();

    public ListAdapter(@NonNull Context context, int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
        this.context = getContext();
        mResource = resource;
        checkboxStates = new boolean[objects.size()];
        filteredStudents = objects;
        studentList = objects;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final ViewHolderItem viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(mResource, parent, false);

            viewHolder = new ViewHolderItem();

            viewHolder.textViewItem = convertView.findViewById(R.id.textView);
            viewHolder.checkBoxItem = convertView.findViewById(R.id.checkBox);


            viewHolder.checkBoxItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, "Checkbox " + position + " clicked!", Toast.LENGTH_SHORT).show();

                    if (viewHolder.checkBoxItem.isChecked()) {
                        studentList.get(position).setPresent(true);
                        //temp.get(position).setPresent(true);
                        bool[position] = true;
                    } else {
                        studentList.get(position).setPresent(false);
                        bool[position] = false;
                        //temp.get(position).setPresent(false);
                    }

                }
            });

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolderItem) convertView.getTag();

        }

        Student concept = filteredStudents.get(position);
        viewHolder.textViewItem.setText(concept.getName());

        viewHolder.checkBoxItem.setChecked(false);

        if (studentList.get(position).isPresent() == true) {
            viewHolder.checkBoxItem.setChecked(true);
        }

        return convertView;
    }

    public void _selectAll(ArrayList<Student> studentLists) {
        for (int i = 0; i < studentLists.size(); i++) {
            studentList.get(i).setPresent(true);
        }
        notifyDataSetChanged();
    }

    public void _unselectAll(ArrayList<Student> studentLists) {
        for (int i = 0; i < studentList.size(); i++) {
            studentList.get(i).setPresent(false);
        }
        notifyDataSetChanged();
    }

    public void update(ArrayList<Student> studentLists) {

        for (int i = 0; i < studentLists.size(); i++) {
            if (bool[i] == true) {
                studentList.get(i).setPresent(true);
            }
        }
        notifyDataSetChanged();

    }

    public Student getItem(int position) {
        return filteredStudents.get(position);
    }

    public int getCount() {
        return filteredStudents.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new StudentsFilter();
        }
        return filter;
    }

    private class StudentsFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<Student> filteredList = new ArrayList<Student>();
                for (int i = 0; i < studentList.size(); i++) {
                    if (studentList.get(i).name.contains(constraint.toString())) {
                        filteredList.add(studentList.get(i));
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;

            } else {
                results.count = studentList.size();
                results.values = studentList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredStudents = (ArrayList<Student>) results.values;
            notifyDataSetChanged();
        }

    }

    static class ViewHolderItem {
        TextView textViewItem;
        CheckBox checkBoxItem;
    }
}

