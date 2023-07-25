package org.example.controller.gerneral;

import org.example.entities.Trader;
import org.example.mapper.TraderMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description: 数据库字段加密
 * @author: ryan
 * @date 2023/6/9 16:12
 * @version: 1.0
 */
@RestController
@RequestMapping("/encrypt")
public class DatabaseFieldEncryptController {

    @Resource
    TraderMapper traderMapper;

    @PostMapping("/insert")
    public void insert(@RequestBody Trader trader) {
        traderMapper.simpleInsert(trader);
    }

    @GetMapping("/query/{id}")
    public Trader queryByPk(@PathVariable(name = "id") Long id) {
        return traderMapper.queryById(id);
    }
}
