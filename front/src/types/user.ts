import { UUID } from "crypto"
import { Comment } from "./comment"
import { Party } from "./party"

export type User = {
  id: UUID
  username: string
  password: string
  email: string
  created_at: Date
  postedComments: Comment[]
  involvedComments: Comment[]
  parties: Party[]
  createdParties: Party[]
}
