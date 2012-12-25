LogAnalyerStateMathine
======================

A log analyer based on statemathine



已经实现的规则
===============
1. 同一IP连续多次触发404请求。
2. 同一IP瞬间的多次访问。


计划实现的规则
==============
1. 同一IP对同一URL的多次连续访问。
2. 同一IP的UserAgent值的变化。（应该允许一个值，比如变了三次或者出现非规则的值）
   因为一个用户可能同时使用两种浏览器。
