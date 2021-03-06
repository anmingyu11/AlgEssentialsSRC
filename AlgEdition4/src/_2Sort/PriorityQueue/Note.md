索引优先队列:

优先队列有一个缺点，就是不能直接访问已存在于优先队列中的对象，并更新它们。

#### 1. 用途

索引优先队列用一个整数和对象进行关联，当我们需要跟新该对象的值时，可以通这个整数进行快速索引，然后对对象的值进行更新。
当然更新后的对象在优先队列中的位置可能发生变化，这样以保证整个队列还是一个优先队列。

#### 2. 简单实现
为了实现快速索引，我们首先尝试一个简单版本。我们创建两个数组分别是pq，keys。
keys的作用是存储对象的引用，**我们将每个对象存储在与之相关的整数作为下标的位置中**，keys存储的对象不一定在数组中连续存放。
pq存储是与对象相关的整数值，注意数组pq是连续存放的。此时pq作为优先队列，但是在上浮和下沉操作中，我们比较的是pq中值作为下标的elements数组中的值。这样我们就可以实现快速索引。

在维护pq中的值时出现了一个问题，我们不知道pq中哪个位置中的值为3，只能从都到尾遍历，找到这个元素所在的位置后进行上浮和下沉操作（因为我们必须通过下标才能快速找到父节点或者孩子节点）。
**为了能够快速找到pq中元素值对应的下标，我们需要额外设置一个数组qp，它的作用是存储与对象相关的整数在pq数组中的下标，并在上浮和下沉的过程中同时维护它。**
