import { UUID } from "crypto"
import { User } from "./user"

export type Comment = {
  id: UUID
  text: string
  rating: number // TODO: Enum Rating
  postingUser: User
  involvedUser: User
}
