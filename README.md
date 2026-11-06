# 𝙒1𝙉𝙎𝙏𝙊𝙉𝙯𝙭𝙘 𝙑𝙞𝙨𝙪𝙖𝙡𝙨

Android launcher prototype for Minecraft Bedrock 1.26.33.1.

## Included
- Dark purple Android launcher screen
- Button to launch installed Minecraft Bedrock
- Optional launch attempt for an installed Atlas app
- Demonstration visual toggles
- GitHub Actions workflow that builds a debug APK

## Build
Push this project to the `main` branch. GitHub Actions will build the APK.
Open the repository's **Actions** tab, select the successful run, and download
the `W1NSTONzxc-Visuals-debug` artifact.

## Important
This is a launcher prototype. The visual switches are currently demonstration
controls; they do not change Minecraft graphics. The app does not inject code
into Minecraft, bypass security, or modify game files. Real resource-pack
support needs to be implemented through Bedrock-compatible pack formats and
supported Android file access.
