# Cargo Ghast

**English** | [简体中文](README.zh-CN.md)

Happy Ghasts that actually fly, turn, and carry a chest like a mule.

Vanilla Happy Ghasts are slow and have nowhere to put your stuff. Cargo Ghast makes a harnessed ghast feel like a real mount, then lets you hang a chest on it the same way you would a donkey.

## Features

- About **3×** vanilla flight speed, with snappier turning
- Look up / down to climb and dive, as in vanilla
- **Sneak + chest** on an adult Happy Ghast attaches a 27-slot inventory
- **Sneak + empty hand** opens that inventory
- While riding a chested ghast, press **E** (inventory key) to open the same 27 slots
- Chest and contents drop if the ghast dies

## Install

1. Install [Fabric Loader](https://fabricmc.net/use/) for your game version.
2. Install the matching [Fabric API](https://modrinth.com/mod/fabric-api).
3. Drop the jar for **that same Minecraft version** into `mods`.

Required on **both server and client**.

## Compatibility

| Minecraft | Java | Loader |
| --- | --- | --- |
| 1.21.6 – 1.21.11 | 21+ | Fabric |
| 26.1, 26.2 | 25+ | Fabric |

Use the jar whose file name contains your game version, for example `cargoghast-1.0.0+1.21.11.jar`.

No Forge / NeoForge build yet.

## Building

JDK 25 is enough to build every target (1.21.x is compiled with `--release 21`).

```
./gradlew build
```

Jars land in `versions/<minecraft>/build/libs/`.

## Releasing

Version lives in `gradle.properties` as `mod.version`. Git tags must match it.

1. Bump `mod.version`.
2. Commit.
3. Create an **annotated** tag. The tag message **is** the GitHub Release body.

Tag name: `vMAJOR.MINOR.PATCH` (example: `v1.0.1`).

Tag message, English, sections omitted if empty:

```
Added
- ...

Changed
- ...

Fixed
- ...
```

Keep each bullet one line, user-facing, no implementation notes.

4. Push the tag:

```
git tag -a v1.0.1 -F notes.txt
git push origin v1.0.1
```

GitHub Actions builds every game version and uploads the remapped jars to that release. Do not attach jars by hand.

## License

[GPL-3.0-only](LICENSE). Copyright LectWolf.
