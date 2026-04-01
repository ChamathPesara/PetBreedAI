<div align="center">

```
██████╗ ███████╗████████╗██████╗ ██████╗ ███████╗███████╗██████╗  █████╗ ██╗
██╔══██╗██╔════╝╚══██╔══╝██╔══██╗██╔══██╗██╔════╝██╔════╝██╔══██╗██╔══██╗██║
██████╔╝█████╗     ██║   ██████╔╝██████╔╝█████╗  █████╗  ██║  ██║███████║██║
██╔═══╝ ██╔══╝     ██║   ██╔══██╗██╔══██╗██╔══╝  ██╔══╝  ██║  ██║██╔══██║██║
██║     ███████╗   ██║   ██████╔╝██║  ██║███████╗███████╗██████╔╝██║  ██║██║
╚═╝     ╚══════╝   ╚═╝   ╚═════╝ ╚═╝  ╚═╝╚══════╝╚══════╝╚═════╝ ╚═╝  ╚═╝╚═╝
```

### 🐾 *Your Pocket AI for Understanding Pets*

<br/>

[![Platform](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://android.com)
[![AI](https://img.shields.io/badge/AI-TensorFlow%20Lite-FF6F00?style=for-the-badge&logo=tensorflow&logoColor=white)](https://www.tensorflow.org/lite)
[![Language](https://img.shields.io/badge/Language-Java-007396?style=for-the-badge&logo=java&logoColor=white)](https://java.com)
[![Model](https://img.shields.io/badge/Model-MobileNet-blueviolet?style=for-the-badge)](https://arxiv.org/abs/1704.04861)
[![Offline](https://img.shields.io/badge/Works-100%25%20Offline-success?style=for-the-badge)](.)

<br/>

> *"What breed is this dog?"* — **Answered in seconds. No internet. No guessing.**

<br/>

</div>

---

## 🌟 The Spark

You're at a park. A fluffy stranger trots over. You think — *"Is that a Samoyed or an Akita?"* — and you have no idea.

**PetBreedAI** was built for exactly that moment.

Point. Tap. Know.

It's an intelligent Android app that transforms your phone into a real-time pet breed expert — powered by deep learning that runs **entirely on-device**, no Wi-Fi required.

---

## 📸 How It Works — In 3 Taps

```
┌─────────────────────────────────────────────────────────┐
│                                                         │
│   📷  SNAP or UPLOAD   →   🧠  AI ANALYZES   →   ✅  DONE  │
│                                                         │
│   Take a photo or          MobileNet model          Breed name,   │
│   pick from gallery        processes image          confidence,   │
│   in one tap               on your device           tips & more   │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

No cloud. No delays. No data leaving your phone.

---

## ✨ Features at a Glance

### 🤖 Intelligent Breed Detection
The AI doesn't just guess — it *thinks*:
- Identifies **37 cat & dog breeds** from the Oxford-IIIT Pet Dataset
- Tells you if it's a **CAT or DOG** automatically
- Shows you a **confidence score** so you know how sure it is
- **Smart filtering**: too uncertain? It says *"Couldn't Identify"* — no fake confidence here

### 📊 Top 3 Predictions
Because life isn't always black and white:
```
🥇  Golden Retriever     ████████████████████  94.2%
🥈  Labrador Retriever   ███░░░░░░░░░░░░░░░░░   4.1%
🥉  Nova Scotia Duck     █░░░░░░░░░░░░░░░░░░░   1.7%
```
See the model's reasoning, not just its top pick.

### 💡 Quick Breed Tips
Every result teaches you something:
> *"Golden Retrievers are highly social. Ensure they get at least 1 hour of exercise daily."* 🐕

### 🕘 Scan History
Every scan is saved locally with:
- 🖼️ Image thumbnail
- 🐾 Breed & animal type
- 📊 Confidence score
- 🕐 Date & timestamp

### 🖼️ Latest Scan Preview
Your home screen shows your most recent result — instant access, always.

### 🔐 User System
- Login & Registration
- Persistent sessions
- Your history stays *yours*

---

## 🧠 The AI Engine

```
┌──────────────────────────────────────────────────────────────┐
│                    UNDER THE HOOD                            │
│                                                              │
│   Architecture  ──►  MobileNet (Pre-trained CNN)             │
│   Dataset       ──►  Oxford-IIIT Pet Dataset                 │
│   Classes       ──►  37 Breeds (Cats & Dogs)                 │
│   Deployment    ──►  TensorFlow Lite (on-device)             │
│   Speed         ──►  ⚡ Fast. Lightweight. Offline.           │
└──────────────────────────────────────────────────────────────┘
```

### Inference Pipeline

```
Your Photo
    │
    ▼
┌─────────────┐
│ Preprocessing│  ← Resize, normalize, tensor conversion
└──────┬──────┘
       │
       ▼
┌─────────────┐
│  TFLite Model│  ← MobileNet forward pass (on-device)
└──────┬──────┘
       │
       ▼
┌─────────────┐
│  Probability │  ← 37 class softmax output
│    Output    │
└──────┬──────┘
       │
       ▼
┌─────────────┐
│  Confidence  │  ← Filter low-confidence results
│   Threshold  │
└──────┬──────┘
       │
       ▼
  Top 3 Results + Tips + History Save ✅
```

---

## ⚙️ Tech Stack

| Layer | Technology |
|-------|-----------|
| **Language** | Java |
| **UI** | XML + Material Design |
| **AI Runtime** | TensorFlow Lite |
| **Model** | MobileNet (Pre-trained CNN) |
| **Image Loading** | Glide |
| **Local Storage** | SQLite |
| **Image Storage** | Internal Storage |

---

## 🎨 Design Philosophy

PetBreedAI isn't just functional — it's *beautiful*:

- 🎨 **Clean, modern UI** with soft gradients & card layouts
- ✨ **Smooth splash animations** for a polished first impression
- 🧭 **Intuitive navigation** via a clean Bottom Navigation Bar (Home · Upload · History)
- 💬 **Smart dialogs** — logout confirmation, graceful exit handling
- 📱 **Minimal yet informative** — every pixel earns its place

**Balance of functionality + aesthetics.** Always.

---

## 🚀 Getting Started

### Prerequisites
- Android Studio (latest stable)
- Android SDK 21+
- Java 8+

### Installation

```bash
# Clone the repository
git clone https://github.com/yourusername/PetBreedAI.git

# Open in Android Studio
File → Open → Select the cloned folder

# Build & Run
Click ▶ Run or use Shift + F10
```

> 💡 No API keys. No internet setup. No configuration. Just run.

---

## 📁 Project Structure

```
PetBreedAI/
├── app/
│   ├── src/main/
│   │   ├── java/
│   │   │   └── com/petbreedai/
│   │   │       ├── activities/       # Login, Register, Home, History
│   │   │       ├── adapters/         # RecyclerView adapters
│   │   │       ├── database/         # SQLite helper & DAO
│   │   │       ├── ml/               # TFLite model wrapper
│   │   │       └── utils/            # Image processing utilities
│   │   ├── res/
│   │   │   ├── layout/               # XML UI layouts
│   │   │   ├── drawable/             # Icons & gradients
│   │   │   └── values/               # Colors, strings, themes
│   │   └── assets/
│   │       └── model.tflite          # On-device AI model
│   └── build.gradle
└── README.md
```

---

## 🔮 What's Coming Next

The roadmap is ambitious:

| Feature | Status |
|---------|--------|
| 🎥 Real-time camera detection | 🔄 Planned |
| 🐾 More animal categories | 🔄 Planned |
| ☁️ Cloud-based model updates | 🔄 Planned |
| 📤 Social sharing features | 🔄 Planned |
| 👤 User profile enhancements | 🔄 Planned |

---

## 🧩 What Makes PetBreedAI Different?

```
✔  Fully offline AI         — Works anywhere, zero data needed
✔  Smart confidence filter  — No misleading guesses, ever
✔  Educational              — Tips & insights with every scan
✔  Clean, modern UX         — Designed for real humans
✔  Built as a real system   — Auth, history, state, the works
```

This isn't a demo. It's a **complete, production-minded application**.

---

## 🤝 Contributing

Contributions are welcome with open paws! 🐾

```bash
# Fork the repo → Create your feature branch
git checkout -b feature/amazing-new-thing

# Commit your changes
git commit -m "Add: amazing new thing"

# Push & open a Pull Request
git push origin feature/amazing-new-thing
```

Please follow the existing code style and add comments where the logic isn't obvious.

---

## 📜 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

---

<div align="center">

### 🐾 PetBreedAI

*Built with curiosity. Powered by AI. Made for pet lovers.*

<br/>

**If this project helped you or made you smile — drop a ⭐ star!**

It means more than you think.

<br/>

*Made with ❤️ and probably too many pictures of cats*

</div>
