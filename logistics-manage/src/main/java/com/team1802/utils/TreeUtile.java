package com.team1802.utils;

import com.team1802.pojo.Node;

import java.util.ArrayList;
import java.util.List;

public class TreeUtile {
    /**
     * @Author  zhl
     * @Description //TODO
     * @Date 2020/12/30
     * @Param nodes :所有的节点列表
     * @return
     */
    public List data(List<Node> nodes) {
        ArrayList<Node> rootNode = new ArrayList<>();
        for(Node node:nodes){
            if(node.getParentId()==0){
                rootNode.add(node);
            }
        }
        for(Node node:rootNode){
            List<Node> child = getChild(node.getId(), nodes);
            node.setChildren(child);
        }
        return rootNode;
    }


    /**
     * @return
     * @Author  zhl
     * @Description //TODO 获取根节点的子节点
     * @Date 2020/12/30
     * @Param
     */
    public List<Node> getChild(Integer id, List<Node> allNode) {
        //存放子菜单的集合
        ArrayList<Node> listChild = new ArrayList<>();
        for (Node node : allNode) {
            if (node.getParentId()==id) {
                listChild.add(node);
            }
        }
        //递归：
        for (Node node : listChild) {
            node.setChildren(getChild(node.getId(), allNode));
        }
        if (listChild.size() == 0) {
            return null;
        }
        return listChild;
    }
}
