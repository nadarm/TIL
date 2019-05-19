package com.myproject.myapplication.myrecyclerview

import android.content.Context
import android.content.Intent
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.myproject.myapplication.CalendarData
import com.myproject.myapplication.R
import com.myproject.myapplication.TodoEditingActivity
import kotlinx.android.synthetic.main.recyclerview_item_daily.view.*
import java.sql.Date

class DailyAdapter(
    private val dateList: List<Date>,
    private val todoList: List<CalendarData>,
    private val context: Context
) :
    RecyclerView.Adapter<DailyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.recyclerview_item_daily, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dateList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dateTextView.text = dateList[position].toString()
        val dailyTodoList = todoList.filter { it.startDate <= dateList[position] && dateList[position] <= it.endDate }
        val childRecyclerManager = LinearLayoutManager(holder.todoRecyclerView.context)
        holder.todoRecyclerView.addItemDecoration(
            DividerItemDecoration(
                holder.todoRecyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
        holder.todoRecyclerView.layoutManager = childRecyclerManager
        holder.todoRecyclerView.adapter = DailyTodoAdapter(dailyTodoList)

        holder.todoAddBtn.setOnClickListener {
            val intent = Intent(context, TodoEditingActivity::class.java)
            intent.putExtra("startDate", dateList[position])
            intent.putExtra("endDate", dateList[position])
            context.startActivity(intent)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.daily_item_date_text_view
        val todoRecyclerView: RecyclerView = itemView.daily_todo_recycler_view
        val todoAddBtn: Button = itemView.btn_daily_todo_add
    }
}