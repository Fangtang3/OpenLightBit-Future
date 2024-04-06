package am9.openLightBit.core

import java.sql.DriverManager
import java.sql.SQLException
import java.time.Instant
import java.util.*
import kotlin.math.floor


/*object BreadFactory {
    var group_ids1: List<String>? = null
    var group_ids2: List<String>? = null
    var speed1: Int = 0
    var speed2: Int = 0

    fun MaterialProduce() {
        val groupids: MutableList<String> = ArrayList()
        try {
            DriverManager.getConnection(
                java.lang.String.format("jdbc:mysql://%s:3306", Global.database_host),
                Global.database_user,
                Global.database_passwd
            ).use { msc ->
                var cmd =
                    msc.prepareStatement(java.lang.String.format("SELECT * FROM `%s`.`material`", Global.database_name))
                var rs = cmd.executeQuery()
                while (rs.next()) {
                    val groupid = rs.getString("gid")
                    groupids.add(groupid)
                    group_ids1 = groupids
                }
                rs.close()
                while (true) {
                    Global.time_now = Instant.now().epochSecond
                    if (group_ids1 != null) {
                        for (group_id in group_ids1!!) {
                            cmd = msc.prepareStatement(
                                java.lang.String.format(
                                    "SELECT * FROM `%s`.`bread` WHERE gid = ?",
                                    Global.database_name
                                )
                            )
                            cmd.setString(1, group_id)
                            rs = cmd.executeQuery()
                            while (rs.next()) {
                                val formula = 4.pow(
                                    rs.getInt("factory_level").toDouble()
                                ) as Int * 2.pow(rs.getInt("output_upgraded").toDouble()) as Int
                                val maxstorage: Int =
                                    (64 * 4.pow((rs.getInt("factory_level") - 1).toDouble()) * 2.pow(
                                        rs.getInt("storage_upgraded").toDouble()
                                    )).toInt()
                                val is_full = rs.getInt("breads") == maxstorage
                                speed1 =
                                    300 - (20 * (rs.getInt("factory_level") - 1)) - (10 * (rs.getInt(
                                        "speed_upgraded"
                                    )))
                                if (rs.getInt("factory_mode") != 2 && !is_full) {
                                    val r = Random()
                                    val random = r.nextInt(1, formula)
                                    rs.close()
                                    cmd = msc.prepareStatement(
                                        java.lang.String.format(
                                            "SELECT * FROM `%s`.`material` WHERE gid = ?",
                                            Global.database_name
                                        )
                                    )
                                    cmd.setString(1, group_id)
                                    rs = cmd.executeQuery()
                                    while (rs.next()) {
                                        if (Global.time_now - rs.getLong("last_produce") >= speed1 && rs.getInt(
                                                "yeast"
                                            ) + random <= formula
                                        ) {
                                            val cmd1 = msc.prepareStatement(
                                                java.lang.String.format(
                                                    "UPDATE `%s`.`material` SET flour = ?, egg = ?, yeast = ?, last_produce = ? WHERE gid = ?;",
                                                    Global.database_name
                                                )
                                            )
                                            cmd1.setInt(1, rs.getInt("flour") + random * 5)
                                            cmd1.setInt(2, rs.getInt("flour") + random * 2)
                                            cmd1.setInt(3, rs.getInt("flour") + random)
                                            cmd1.setLong(4, Global.time_now)
                                            cmd1.setString(5, group_id)
                                            cmd1.executeUpdate()
                                        } else if (Global.time_now - rs.getLong("last_produce") >= speed1 && rs.getInt(
                                                "yeast"
                                            ) + random > formula
                                        ) {
                                            val cmd1 = msc.prepareStatement(
                                                java.lang.String.format(
                                                    "UPDATE `%s`.`material` SET flour = ?, egg = ?, yeast = ?, last_produce = ? WHERE gid = ?;",
                                                    Global.database_name
                                                )
                                            )
                                            cmd1.setInt(1, formula * 5)
                                            cmd1.setInt(2, formula * 2)
                                            cmd1.setInt(3, formula)
                                            cmd1.setLong(4, Global.time_now)
                                            cmd1.setString(5, group_id)
                                            cmd1.executeUpdate()
                                        }
                                    }
                                    rs.close()
                                }
                            }
                            rs.close()
                        }
                    }
                    try {
                        Thread.sleep(500)
                    } catch (e: InterruptedException) {
                        throw RuntimeException(e)
                    }
                }
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    fun BreadProduce() {
        val groupids: MutableList<String> = ArrayList()
        try {
            DriverManager.getConnection(
                java.lang.String.format("jdbc:mysql://%s:3306", Global.database_host),
                Global.database_user,
                Global.database_passwd
            ).use { msc ->
                var cmd =
                    msc.prepareStatement(java.lang.String.format("SELECT * FROM `%s`.`bread`", Global.database_name))
                var rs = cmd.executeQuery()
                while (rs.next()) {
                    val groupid = rs.getString("gid")
                    groupids.add(groupid)
                    group_ids2 = groupids
                }
                rs.close()
                while (true) {
                    Global.time_now = Instant.now().epochSecond
                    if (group_ids2 != null) {
                        for (group_id in group_ids2!!) {
                            cmd = msc.prepareStatement(
                                java.lang.String.format(
                                    "SELECT * FROM `%s`.`bread` WHERE gid = ?",
                                    Global.database_name
                                )
                            )
                            cmd.setString(1, group_id)
                            rs = cmd.executeQuery()
                            while (rs.next()) {
                                if (rs.getInt("factory_mode") != 2) {
                                    speed2 =
                                        300 - (20 * (rs.getInt("factory_level") - 1)) - (10 * (rs.getInt(
                                            "speed_upgraded"
                                        )))
                                    val maxstorage: Int =
                                        (64 * 4.pow((rs.getInt("factory_level") - 1).toDouble()) * 2.pow(
                                            rs.getInt("storage_upgraded").toDouble()
                                        )).toInt()
                                    val bread_diversity = rs.getInt("factory_mode")
                                    rs.close()
                                    var random = 0
                                    val r = Random()
                                    cmd = msc.prepareStatement(
                                        java.lang.String.format(
                                            "SELECT * FROM `%s`.`material` WHERE gid = ?",
                                            Global.database_name
                                        )
                                    )
                                    cmd.setString(1, group_id)
                                    rs = cmd.executeQuery()
                                    while (rs.next()) {
                                        if (floor(rs.getInt("yeast") / 4.pow(bread_diversity.toDouble())) == 1) {
                                            random = 1
                                        } else if (rs.getInt("yeast") > 1) {
                                            random = r.nextInt(
                                                1,
                                                floor(rs.getInt("yeast") / 4.pow(bread_diversity.toDouble()))
                                                    .toInt()
                                            )
                                        }
                                        rs.close()
                                        cmd = msc.prepareStatement(
                                            java.lang.String.format(
                                                "SELECT * FROM `%s`.`bread` WHERE gid = ?",
                                                Global.database_name
                                            )
                                        )
                                        cmd.setString(1, group_id)
                                        rs = cmd.executeQuery()
                                        while (rs.next()) {
                                            if (Global.time_now - rs.getLong("last_produce") >= speed2) {
                                                rs.close()
                                                cmd = msc.prepareStatement(
                                                    java.lang.String.format(
                                                        "SELECT * FROM `%s`.`bread` WHERE gid = ?",
                                                        Global.database_name
                                                    )
                                                )
                                                cmd.setString(1, group_id)
                                                rs = cmd.executeQuery()
                                                while (rs.next()) {
                                                    if (rs.getInt("breads") + random < maxstorage) {
                                                        var cmd1 = msc.prepareStatement(
                                                            java.lang.String.format(
                                                                "UPDATE `%s`.`bread` SET breads = ? WHERE gid = ?;",
                                                                Global.database_name
                                                            )
                                                        )
                                                        cmd1.setInt(1, rs.getInt("breads") + random)
                                                        cmd1.executeUpdate()
                                                        rs.close()
                                                        cmd = msc.prepareStatement(
                                                            java.lang.String.format(
                                                                "SELECT * FROM `%s`.`bread` WHERE gid = ?",
                                                                Global.database_name
                                                            )
                                                        )
                                                        cmd.setString(1, group_id)
                                                        rs = cmd.executeQuery()
                                                        while (rs.next()) {
                                                            cmd1 = msc.prepareStatement(
                                                                java.lang.String.format(
                                                                    "UPDATE `%s`.`material` SET flour = ?, egg = ?, yeast = ? WHERE gid = ?;",
                                                                    Global.database_name
                                                                )
                                                            )
                                                            cmd1.setInt(
                                                                1,
                                                                (rs.getInt("flour") - random * 5 * 4.pow(bread_diversity.toDouble())).toInt()
                                                            )
                                                            cmd1.setInt(
                                                                2,
                                                                (rs.getInt("egg") - random * 2 * 4.pow(bread_diversity.toDouble())).toInt()
                                                            )
                                                            cmd1.setInt(
                                                                3,
                                                                (rs.getInt("yeast") - random * 4.pow(bread_diversity.toDouble())).toInt()
                                                            )
                                                            cmd1.setString(4, group_id)
                                                            cmd1.executeUpdate()
                                                        }
                                                    } else if (rs.getInt("breads") + random >= maxstorage) {
                                                        val difference = maxstorage - rs.getInt("breads")
                                                        var cmd1 = msc.prepareStatement(
                                                            java.lang.String.format(
                                                                "UPDATE `%s`.`bread` SET breads = ? WHERE gid = ?;",
                                                                Global.database_name
                                                            )
                                                        )
                                                        cmd1.setInt(1, maxstorage)
                                                        cmd1.setString(2, group_id)
                                                        cmd1.executeUpdate()
                                                        rs.close()
                                                        cmd = msc.prepareStatement(
                                                            java.lang.String.format(
                                                                "SELECT * FROM `%s`.`bread` WHERE gid = ?",
                                                                Global.database_name
                                                            )
                                                        )
                                                        cmd.setString(1, group_id)
                                                        rs = cmd.executeQuery()
                                                        while (rs.next()) {
                                                            cmd1 = msc.prepareStatement(
                                                                java.lang.String.format(
                                                                    "UPDATE `%s`.`material` SET flour = ?, egg = ?, yeast = ? WHERE gid = ?;",
                                                                    Global.database_name
                                                                )
                                                            )
                                                            cmd1.setInt(
                                                                1,
                                                                (rs.getInt("flour") - difference * 5 * 4.pow(
                                                                    bread_diversity.toDouble()
                                                                )).toInt()
                                                            )
                                                            cmd1.setInt(
                                                                2,
                                                                (rs.getInt("egg") - difference * 2 * 4.pow(
                                                                    bread_diversity.toDouble()
                                                                )).toInt()
                                                            )
                                                            cmd1.setInt(
                                                                3,
                                                                (rs.getInt("yeast") - difference * 4.pow(bread_diversity.toDouble())).toInt()
                                                            )
                                                            cmd1.setString(4, group_id)
                                                            cmd1.executeUpdate()
                                                        }
                                                    }
                                                    val cmd1 = msc.prepareStatement(
                                                        java.lang.String.format(
                                                            "UPDATE `%s`.`bread` SET last_produce = ? WHERE gid = ?;",
                                                            Global.database_name
                                                        )
                                                    )
                                                    cmd1.setLong(1, Instant.now().epochSecond)
                                                    cmd1.setString(2, group_id)
                                                    cmd1.executeUpdate()
                                                    rs.close()
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    try {
                        Thread.sleep(500)
                    } catch (e: InterruptedException) {
                        throw RuntimeException(e)
                    }
                }
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }
}*/