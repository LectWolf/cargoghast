# Cargo Ghast

[English](README.md) | **简体中文**

让快乐恶魂真正飞得动、转得过，并能像骡子一样挂箱子。

原版快乐恶魂又慢又没地方放东西。Cargo Ghast 让套上挽具的恶魂骑起来像坐骑，再按骡/驴那套把箱子挂上去。

## 功能

- 飞行速度大约是原版的 **3 倍**，转向更跟手
- 抬头 / 低头爬升下潜，和原版一样
- 对着成年快乐恶魂 **潜行 + 箱子**：挂上 27 格库存
- **潜行 + 空手**：打开这份库存
- 骑着已挂箱的恶魂时按 **E**（物品栏键），打开同一份 27 格
- 恶魂死亡时掉落箱子和里面的物品

## 安装

1. 按游戏版本安装 [Fabric Loader](https://fabricmc.net/use/)。
2. 安装对应版本的 [Fabric API](https://modrinth.com/mod/fabric-api)。
3. 把 **同一 Minecraft 版本** 的 jar 放进 `mods`。

**服务器和客户端都要装。**

## 兼容

| Minecraft | Java | 加载器 |
| --- | --- | --- |
| 1.21.6 – 1.21.11 | 21+ | Fabric |
| 26.1、26.2 | 25+ | Fabric |

认 jar 文件名里的游戏版本，例如 `cargoghast-1.0.0+1.21.11.jar`。

目前没有 Forge / NeoForge。

## 编译

用 JDK 25 即可编全部目标（1.21.x 使用 `--release 21`）。

```
./gradlew build
```

产物在 `versions/<minecraft>/build/libs/`。

## 发版

版本号在 `gradle.properties` 的 `mod.version`。Git 标签必须和它一致。

1. 改 `mod.version`。
2. 提交。
3. 打 **附注标签**。标签说明 **就是** GitHub Release 正文。

标签名：`v主版本.次版本.补丁`（例如 `v1.0.1`）。

说明用英文，空的段落整段删掉：

```
Added
- ...

Changed
- ...

Fixed
- ...
```

每条一行，写给玩家看，不要写实现细节。

4. 推送标签：

```
git tag -a v1.0.1 -F notes.txt
git push origin v1.0.1
```

GitHub Actions 会编译所有游戏版本，并把 remap 后的 jar 传到该 Release。不要手工传 jar。

## 许可

[GPL-3.0-only](LICENSE)。Copyright LectWolf.
