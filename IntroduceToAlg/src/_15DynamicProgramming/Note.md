## 动态规划

- 动态规划算法的四个步骤
    1. 刻画一个最优解的结构特征
    2. 递归地定义最优解的值
    3. 计算最优解的值,通常采用自底向上的方法
    4. 利用计算出的信息构造一个最优解.
    5. 画出子问题图(自己加上去的)

- 动态规划的两种等价的实现方法
    1. 备忘录法
    2. 自底向上法

- 子问题图


### 动态规划原理

最关键的两要素,最优子结构和子问题重叠.

#### 最优子结构
- 如果一个问题的最优解包含其子问题的最优解,我们就称此问题具有最优子结构性质.

- 在发掘最优子结构性质的过程中,实际上遵循了如下的通用模式:
    1. 证明问题最优解的第一部分是做了一个选择,比如且钢条第一次的切割位置,选择矩阵连.
    2. 对于一个给定问题,在其可能的第一个选择中,你假定已经直到哪种选择才会得到最优解.你现在并不关心这种选择具体是如何得到的,知识假定已经知道了这种选择.
    3. 给定可获得最优解的选择后,你确定这次选择会产生哪些子问题,以及如何最好地刻画子问题空间.
    4. 利用"剪切-粘贴"技术证明:作为构成原问题最优解的组成部分,每个子问题的解就是它本身的最优解.证明://Todo

一个刻画子问题空间的好经验是:保持子问题空间尽可能简单,只在必要时才扩展它,例如://Todo

对于不同问题领域,最优子结构的不同体现在两个方面:
1. 原问题的最优解中涉及多少个子问题
2. 在确定最优解使用哪些子问题时,我们需要考察多少种选择.

#### 子问题的相关性

#### 重叠子问题

适合用动态规划方法求解的最优化问题应该具备第二个性质是子问题的空间必须足够"小",即问题的递归算法会反复地求解相同的子问题,而不是一直生成新的子问题.

- 如果递归算法反复求解相同的子问题,我们就称最优化问题具有重叠子问题性质.与之相对的,适合用分治方法求解的问题通常在递归的每一步都生成全新的子问题.
动态规划算法通常这样利用重叠子问题性质:对每个子问题求解一次,将解存入一个表中,当再次需要这个子问题时直接查表,每次查表的代价为常量时间.

#### 贪心算法和动态规划最大的不同

贪心法并不是首先寻找子问题的最优解,然后再在其中进行选择,而是首先做出一次贪心选择---在当时看起来最优的选择---然后求解选出的子问题,从而不必费心求解所有可能相关的子问题

### 钢条切割

### 矩阵链乘法

### 最优二叉搜索树
