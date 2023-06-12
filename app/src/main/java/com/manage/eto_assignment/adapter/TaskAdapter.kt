package com.manage.eto_assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.manage.eto_assignment.R
import com.manage.eto_assignment.databinding.TaskItemLayoutBinding
import com.manage.eto_assignment.ui.state.TaskUiState

class TaskAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class TaskDiffCallback(private val oldItems: List<TaskUiState>, private val newItems: List<TaskUiState>) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldItems.size
        }

        override fun getNewListSize(): Int {
            return newItems.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition].id == newItems[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition] == newItems[newItemPosition]
        }
    }
    // Define the view types
    private val headerView = 1
    private val itemView = 2
    private var listener: ((task: TaskUiState,onComplete:()->Unit) -> Unit)? = null
    private var items :List<TaskUiState> = mutableListOf()


    fun updateItems(newItems: List<TaskUiState>) {
        val diffCallback = TaskDiffCallback(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items = newItems
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }
    // Create the ViewHolders for each view type
    inner class ViewHolderOne(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Bind ViewHolderOne views here
    }

    inner class TaskItemViewHolder(itemView: TaskItemLayoutBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        // Bind ViewHolderTwo views here
        val bind = itemView
    }

    // Override the getItemViewType method to return the view type based on position
    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            headerView
        } else {
            itemView
        }
    }

    fun setListener(callback: (task: TaskUiState,onComplete:()->Unit) -> Unit) {
        listener = callback
    }

    // Create and return the appropriate ViewHolder based on the view type
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            headerView -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.header_layout, parent, false)
                ViewHolderOne(view)
            }
            itemView -> {
                val view = DataBindingUtil.inflate<TaskItemLayoutBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.task_item_layout,
                    parent,
                    false
                )
                TaskItemViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    // Bind the data to the appropriate ViewHolder based on the view type
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            headerView -> {
                val viewHolderOne = holder as ViewHolderOne
                // Bind data for ViewHolderOne
            }
            itemView -> {

                val viewHolderTwo = holder as TaskItemViewHolder
                // Bind data for ViewHolderTwo
                // The view that triggers the popup menu
                val popupMenu = PopupMenu(context, viewHolderTwo.bind.taskAction)

// Inflate the menu resource
                popupMenu.menuInflater.inflate(R.menu.task_menu, popupMenu.menu)

// Set a listener to handle menu item clicks
                popupMenu.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.update_menu -> {
                            // Handle item 1 click
                            listener?.let { it(items[position - 1]) {
                                notifyItemChanged(position - 1)
                            }
                            }
                            true
                        }
                        R.id.cancel_menu -> {
                            // Handle item 2 click
                            true
                        }
                        else -> false
                    }
                }

// Show the popup menu

                viewHolderTwo.bind.task = items[position - 1]
                viewHolderTwo.bind.taskAction
                    .setOnClickListener {
                        popupMenu.show()
                    }
                viewHolderTwo.bind.executePendingBindings()
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size + 1
    }
}
