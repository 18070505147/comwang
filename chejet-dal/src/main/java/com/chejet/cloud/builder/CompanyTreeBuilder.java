package com.chejet.cloud.builder;

import com.chejet.cloud.vo.CompanyVO;

import java.util.*;

public class CompanyTreeBuilder {

    public static List<CompanyVO> toTreeList(List<CompanyVO> companyList) {
        Map<Object, CompanyVO> maps = new HashMap<>();
        List<CompanyVO> list = new ArrayList<>();
        builderTreeStrcture(companyList, list);
        return list;
    }

    /**
     * 构造树形结构
     * <p>
     * 所有节点必须严格归属一棵树即任何一个几点必须有父节点，或者是根节点。否则改算法会死循环。
     *
     * @param companys
     * @param result
     * @version map版
     */
    public static void builderTreeStrcture(List<CompanyVO> companys, List<CompanyVO> result) {
        Map<Long, CompanyVO> alreadyNodeMap = new HashMap<>();
        while (alreadyNodeMap.size() != companys.size()) {
            for (CompanyVO node : companys) {
                if (null == node.getParentId()) {
                    result.add(node);
                    alreadyNodeMap.put(node.getId(), node);
                } else {
                    //父节点已经在树中
                    if (alreadyNodeMap.containsKey(node.getParentId())) {
                        CompanyVO parent = alreadyNodeMap.get(node.getParentId());
                        parent.addChildren(node);
                        alreadyNodeMap.put(node.getId(), node);
                    }
                }
            }
        }
    }

    /**
     * 构造树形结构
     * <p>
     * 所有节点必须严格归属一棵树即任何一个几点必须有父节点，或者是根节点。否则改算法会死循环。
     *
     * @param nodes
     * @param result
     * @version 队列版本(未测)
     */
    public static void buildTree(List<CompanyVO> nodes, List<CompanyVO> result) {
        Map<Long, CompanyVO> alreadyNodeMap = new HashMap<>();
        Queue<CompanyVO> nodeQueue = new ArrayDeque<>();
        for (CompanyVO node : nodes) {
            ((ArrayDeque<CompanyVO>) nodeQueue).addLast(node);
        }
        while (!nodeQueue.isEmpty()) {
            //根节点
            CompanyVO node = ((ArrayDeque<CompanyVO>) nodeQueue).peekFirst();
            if (Integer.valueOf(1).equals(node.getLeftValue())) {
                result.add(node);
                alreadyNodeMap.put(node.getId(), node);
            } else {
                //父节点已在树中
                if (alreadyNodeMap.containsKey(node.getParentId())) {
                    CompanyVO parent = alreadyNodeMap.get(node.getParentId());
                    parent.addChildren(node);
                    alreadyNodeMap.put(node.getId(), node);
                } else {
                    ((ArrayDeque<CompanyVO>) nodeQueue).addLast(node);
                }
            }
        }
    }
}
