import java.awt.Color

class TreeNode<T>(val name: String, val value: T){
    private val nodes: MutableMap<String, TreeNode<T>> = mutableMapOf()

    var parent: TreeNode<T>? = null
    val count: Int get() = nodes.size
    val names: Set<String> = nodes.keys
    val children: Collection<TreeNode<T>> = nodes.values

    fun addChild(node: TreeNode<T>) {
        nodes[node.name] = node
        node.parent = this
    }

    fun addChild(name: String, value: T) = addChild(TreeNode(name, value))

    fun addChild(pair: Pair<String, T>) = addChild(pair.first, pair.second)

    fun addChildren(nodes: Map<String, T>) = nodes.forEach { (name, value) -> addChild(name, value) }

    fun addChildren(vararg nodes: Pair<String, T>) = addChildren(nodes.toMap())

    operator fun plusAssign(node: TreeNode<T>) = addChild(node)

    operator fun plusAssign(nodes: Map<String, T>) = nodes.forEach{ addChild(it.toPair()) }

    operator fun contains(node: TreeNode<T>): Boolean = node in nodes.values

    fun clearChildren() = nodes.clear()

    fun removeChild(name: String) = nodes.remove(name)

    operator fun get(name: String): TreeNode<T>? = nodes[name]

    operator fun set(name: String, node: TreeNode<T>?) {
        nodes.remove(name)
        if (node != null) {
            nodes[name] = node
            node.parent = this
        }
    }

    operator fun set(name: String, value: T) = set(name, TreeNode(name, value))

    inline fun forEach(it: (TreeNode<T>) -> Unit) = children.forEach { node -> it(node) }

    override fun toString(): String {
        var output = name
        if (count != 0)
        {
            output += " -> "
            val temp = arrayListOf<String>()
            forEach { temp.add(it.toString()) }
            output += "[ ${temp.joinToString(", ")} ]"
        }
        return output
    }
}

class ColorPalette() {
    val background = Color(236, 229, 199)
    val backgroundAlt = Color(205, 194, 174)
    val accent = Color(194, 222, 209)
    val foreground = Color(53, 66, 89)
}