package com.example.cursor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class TAdapter extends ArrayAdapter<String> implements Filterable {

    private List<String> items;
    private List<String> filtereditems;
    private ModelFilter filter;
    private LayoutInflater inflater;
    ArrayList<String> strings;

    TAdapter(Activity context, ArrayList<String> list, ArrayList<String> list1) {
        super(context, R.layout.list_item, list);
        items = new ArrayList<>();
        items.addAll(list);
        for (int i = 0; i < items.size(); i++) {
        }
        strings = list1;
        filtereditems = new ArrayList<>();
        filtereditems.addAll(items);
        inflater = context.getLayoutInflater();
        getFilter();
    }
    @Override
    public Filter getFilter() {
        if (filter == null){
            filter  = new ModelFilter();
        }
        return filter;
    }
    static class ViewHolder {
        protected TextView textname;
        protected TextView textsub;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        String m = filtereditems.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            view = inflater.inflate(R.layout.list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.textname = view.findViewById(R.id.nameitem);
            viewHolder.textsub = view.findViewById(R.id.subitem);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = ((ViewHolder) view.getTag());
        }
        viewHolder.textname.setText(m);
        viewHolder.textsub.setText(strings.get(position));
        return view;
    }
    private class ModelFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            constraint = constraint.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if(constraint.toString().length() > 0)
            {
                ArrayList<String> filteredItems = new ArrayList<>();

                for(int i = 0, l = items.size(); i < l; i++)
                {
                    String m = items.get(i);
                    String[] s = m.split(" ");
                    for (String s1: s)
                        if(s1.toLowerCase().indexOf(String.valueOf(constraint)) == 0)
                            filteredItems.add(m);
                    if(strings.get(i).toLowerCase().contains(constraint)){
                        filteredItems.add(m);
                    }

                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            }
            else
            {
                synchronized(this)
                {
                    result.values = items;
                    result.count = items.size();
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            filtereditems = (ArrayList<String>)results.values;
            notifyDataSetChanged();
            clear();
            for(int i = 0, l = filtereditems.size(); i < l; i++)
                add(filtereditems.get(i));
            notifyDataSetInvalidated();
        }
    }
}