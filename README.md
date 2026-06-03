# ✦ PostMind — AI-Powered Social Publishing Platform

> _Write less. Say more. Let AI think with you._

PostMind is a next-generation social platform where users craft, schedule, and share AI-assisted posts — blending the power of generative content with real social interaction.

---

## 🚀 What is PostMind?

PostMind lets users generate high-quality social content using AI by simply providing a topic and tone. Once generated, content can be enhanced, image-enriched, posted instantly, saved, or scheduled for later — all within a clean social feed where others can like, comment, share, and repost.

---

## 🗺️ How It Works — User Flow

```
                            ┌──────────┐
                            │   User   │
                            └────┬─────┘
         ┌──────────┬────────────┼──────────────┬─────────────────┐
         ▼          ▼            ▼               ▼                 ▼
  ┌─────────────┐ ┌───────┐ ┌────────────┐ ┌──────────────────┐ ┌──────────────────────┐
  │Follow/Unfollow│ │Profile│ │  Dashboard │ │Post with AI      │ │ Comment/Like/        │
  └─────────────┘ └───────┘ └────────────┘ │(Provide Topic)   │ │ Share/Repost         │
                                            └────────┬─────────┘ └──────────────────────┘
                                                     │
                                                     ▼
                                            ┌─────────────────┐
                                            │   AI Content    │
                                            │   Generated     │
                                            └────┬────────────┘
                                      ┌──────────┴──────────┐
                                      ▼                      ▼
                            ┌──────────────────┐   ┌──────────────────────┐
                            │ Enhance Content  │   │ Add / Edit Image     │
                            │(Simple/Complex)  │   │                      │
                            └──────────────────┘   └──────────┬───────────┘
                                                               │
                             ┌─────────────────────────────────┤
                             ▼                                 ▼                         ▼
                      ┌────────────┐                   ┌──────────┐          ┌─────────────────────────┐
                      │    Post    │                   │   Save   │          │  Schedule to Post Later  │
                      └────────────┘                   └──────────┘          └─────────────────────────┘
```

---

## 🧱 Tech Stack

| Layer        | Technology                          |
|--------------|--------------------------------------|
| Frontend     | React (Vite)                        |
| Backend      | Spring Boot                          |
| Build Tool   | Maven                                |
| Database     | PostgreSQL                           |
| AI Layer     | Claude API (Anthropic)              |

---

## 🗄️ Database Overview

| Table              | Purpose                                          |
|--------------------|--------------------------------------------------|
| `users`            | User accounts, profiles, and credentials         |
| `ai_generations`   | AI-generated content per topic/tone/platform     |
| `post`             | Published posts (manual or AI-assisted)          |
| `comments`         | User comments on posts                           |
| `likes`            | Post likes                                       |
| `save_post`        | Bookmarked/saved posts                           |
| `repost`           | Reposted content                                 |
| `follows`          | Follow/unfollow relationships                    |
| `scheduled_posts`  | Posts queued for future publishing               |

---

## ✨ Core Features

- 🤖 **AI Post Generation** — Input a topic, pick a tone, get platform-ready content
- ✏️ **Content Enhancement** — Choose simple, enhanced, or complex rewrites
- 🖼️ **Image Support** — Attach and edit images alongside your post
- 📅 **Post Scheduling** — Set a future time for auto-publishing
- 💾 **Save Drafts** — Save posts to revisit and publish later
- 👥 **Social Graph** — Follow users, see their content in your feed
- 💬 **Engagement** — Like, comment, share, and repost

---

## 📁 Project Structure (High Level)

```
postmind/
├── frontend/               # React app
│   ├── src/
│   │   ├── pages/          # Dashboard, Profile, Feed
│   │   ├── components/     # Post card, AI editor, scheduler
│   │   └── api/            # Axios API calls
├── backend/                # Spring Boot app
│   ├── src/main/java/
│   │   ├── controllers/    # REST endpoints
│   │   ├── services/       # Business logic + AI integration
│   │   ├── models/         # JPA Entities
│   │   └── repositories/   # Spring Data JPA repos
│   └── pom.xml             # Maven config
└── README.md
```

---

## ⚡ Getting Started

```bash
# Clone the repo
git clone https://github.com/your-username/postmind.git

# Backend
cd backend
mvn spring-boot:run

# Frontend
cd frontend
npm install
npm run dev
```

> Make sure PostgreSQL is running and your `application.properties` has the correct DB credentials.

---

## 📄 License

MIT © PostMind
