package am9.openLightBit.data

import java.sql.Connection
import java.sql.Statement

class SQL {
    companion object {
        lateinit var c: Connection
        lateinit var s: Statement
        fun main() {
            //from 2kbit-java
            val sql = arrayOf<String>(
                java.lang.String.format(
                    """
                            CREATE TABLE IF NOT EXISTS `%s`.`blocklist` (
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `qid` VARCHAR(10) NOT NULL COMMENT 'QQ号',
                            `gid` VARCHAR(10) NOT NULL COMMENT 'Q群号',
                            PRIMARY KEY (`id`));
                            
                            """.trimIndent(), ConfigYaml.cloudConfig["mysql-dbname"]
                ),
                java.lang.String.format(
                    """
                            CREATE TABLE IF NOT EXISTS `%s`.`ops` (
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `qid` VARCHAR(10) NOT NULL COMMENT 'QQ号',
                            `gid` VARCHAR(10) NOT NULL COMMENT 'Q群号',
                            PRIMARY KEY (`id`));
                            
                            """.trimIndent(), ConfigYaml.cloudConfig["mysql-dbname"]
                ),
                java.lang.String.format(
                    """
                            CREATE TABLE IF NOT EXISTS `%s`.`ignores` (
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `qid` VARCHAR(10) NOT NULL COMMENT 'QQ号',
                            `gid` VARCHAR(10) NOT NULL COMMENT 'Q群号',
                            PRIMARY KEY (`id`));
                            
                            """.trimIndent(), ConfigYaml.cloudConfig["mysql-dbname"]
                ),
                java.lang.String.format(
                    """
                            CREATE TABLE IF NOT EXISTS `%s`.`ignores` (
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `qid` VARCHAR(10) NOT NULL COMMENT 'QQ号',
                            `gid` VARCHAR(10) NOT NULL COMMENT 'Q群号',
                            PRIMARY KEY (`id`));
                            
                            """.trimIndent(), ConfigYaml.cloudConfig["mysql-dbname"]
                ),
                java.lang.String.format(
                    """
                            CREATE TABLE IF NOT EXISTS `%s`.`g_blocklist` (
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `qid` VARCHAR(10) NOT NULL COMMENT 'QQ号',
                            PRIMARY KEY (`id`));
                            
                            """.trimIndent(), ConfigYaml.cloudConfig["mysql-dbname"]
                ),
                java.lang.String.format(
                    """
                            CREATE TABLE IF NOT EXISTS `%s`.`g_ops` (
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `qid` VARCHAR(10) NOT NULL COMMENT 'QQ号',
                            PRIMARY KEY (`id`));
                            
                            """.trimIndent(), ConfigYaml.cloudConfig["mysql-dbname"]
                ),
                java.lang.String.format(
                    """
                            CREATE TABLE IF NOT EXISTS `%s`.`g_ignores` (
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `qid` VARCHAR(10) NOT NULL COMMENT 'QQ号',
                            PRIMARY KEY (`id`));
                            
                            """.trimIndent(), ConfigYaml.cloudConfig["mysql-dbname"]
                ),
                java.lang.String.format(
                    """
                            CREATE TABLE IF NOT EXISTS `%s`.`repeatctrl` (
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `qid` VARCHAR(10) NOT NULL COMMENT 'QQ号',
                            `gid` VARCHAR(10) NOT NULL COMMENT 'Q群号',
                            `last_repeat` BIGINT NULL COMMENT '上次复读时间',
                            `last_repeatctrl` BIGINT NULL COMMENT '上次复读控制时间',
                            `repeat_count` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '复读计数',
                            PRIMARY KEY (`id`));
                            
                            """.trimIndent(), ConfigYaml.cloudConfig["mysql-dbname"]
                ),
                java.lang.String.format(
                    """
                            CREATE TABLE IF NOT EXISTS `%s`.`bread` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `gid` varchar(10) NOT NULL COMMENT 'Q群号',
                            `factory_level` int NOT NULL DEFAULT '1' COMMENT '面包厂等级',
                            `storage_upgraded` int NOT NULL DEFAULT '0' COMMENT '库存升级次数',
                            `speed_upgraded` int NOT NULL DEFAULT '0' COMMENT '生产速度升级次数',
                            `output_upgraded` int NOT NULL DEFAULT '0' COMMENT '产量升级次数',
                            `factory_mode` tinyint NOT NULL DEFAULT '0' COMMENT '面包厂生产模式',
                            `factory_exp` int NOT NULL DEFAULT '0' COMMENT '面包厂经验',
                            `breads` int NOT NULL DEFAULT '0' COMMENT '面包库存',
                            `exp_gained_today` int NOT NULL DEFAULT '0' COMMENT '近24小时获取经验数',
                            `last_expfull` bigint NOT NULL DEFAULT '946656000' COMMENT '上次达到经验上限时间',
                            `last_expgain` bigint NOT NULL DEFAULT '946656000' COMMENT '近24小时首次获取经验时间',
                            `last_produce` bigint NOT NULL DEFAULT '946656000' COMMENT '上次完成一轮生产周期时间',
                            PRIMARY KEY (`id`))
                            
                            """.trimIndent(), ConfigYaml.cloudConfig["mysql-dbname"]
                ),
                java.lang.String.format(
                    """
                            CREATE TABLE IF NOT EXISTS `%s`.`material` (
                              `id` INT NOT NULL AUTO_INCREMENT,
                              `gid` VARCHAR(10) NOT NULL COMMENT 'Q群号',
                              `flour` INT NOT NULL DEFAULT 0 COMMENT '面粉数量',
                              `egg` INT NOT NULL DEFAULT 0 COMMENT '鸡蛋数量',
                              `yeast` INT NOT NULL DEFAULT 0 COMMENT '酵母数量',
                              `last_produce` bigint NOT NULL DEFAULT '946656000' COMMENT '上次完成一轮生产周期时间',
                              PRIMARY KEY (`id`));
                            
                            """.trimIndent(), ConfigYaml.cloudConfig["mysql-dbname"]
                ),
                java.lang.String.format(
                    "INSERT IGNORE INTO `%s`.`material` (id, gid) SELECT id, gid FROM `%s`.`bread`",
                    ConfigYaml.cloudConfig["mysql-dbname"],
                    ConfigYaml.cloudConfig["mysql-dbname"]
                )
            )
            for (exe: String in sql) {
                s.executeUpdate(exe)
            }
        }
    }
}