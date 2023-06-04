package com.javapandeng.utils;

// 使用MySQL的binlog监听数据库变化
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.github.shyiko.mysql.binlog.event.TableMapEventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class OrderUpdateListener {

/*    // MySQL的binlog配置
    private final String host = "127.0.0.1";
    private final int port = 3306;
    private final String username = "root";
    private final String password = "password";
    private final String schema = "test";
    // 监听的表名
    private final String table = "order";
    // 前端WebSocket连接管理器
    @Autowired
    private WebSocketHandler handler;
    // 启动监听
    @PostConstruct
    public void start() throws IOException {
        BinaryLogClient client = new BinaryLogClient(host, port, username, password);
        client.setKeepAliveInterval(TimeUnit.MINUTES.toMillis(1));
        client.registerEventListener(event -> {
            EventData data = event.getData();
            if (data instanceof TableMapEventData) {
                TableMapEventData tableData = (TableMapEventData) data;
                if (tableData.getTable().equals(table)) {
                    // 记录下表名对应的表ID，用于后面过滤该表的事件
                    long tableId = tableData.getTableId();
                }
            } else if (data instanceof UpdateRowsEventData) {
                UpdateRowsEventData updateData = (UpdateRowsEventData) data;
                if (updateData.getTableId() == tableId) {
                    // 向前端推送通知
                    handler.sendMessageToAll("order表已更新");
                }
            }
        });
        client.connect();
    }*/
}
