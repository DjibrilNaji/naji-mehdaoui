import { UUID } from "crypto"
import { Party } from "./party"

export type Photo = {
  id: UUID
  url: string
  party: Party
}
