package com.eventplanner

/*
class EventAdapter(
    private val onItemClick: (Event) -> Unit,
    private val onItemLongClick: (Event) -> Unit
) : ListAdapter<Event, EventAdapter.EventViewHolder>(DiffCallback()) {

    class EventViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(event: Event) {
            view.findViewById<AppCompatTextView>(R.id.titleText).text = event.title
            view.findViewById<AppCompatTextView>(R.id.descriptionText).text = event.description
            view.findViewById<AppCompatTextView>(R.id.timeText).text = event.time

            view.setOnClickListener { onItemClick(event) }
            view.setOnLongClickListener {
                onItemLongClick(event)
                true
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Event, newItem: Event) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_items, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}*/
