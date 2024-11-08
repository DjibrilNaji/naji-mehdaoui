import { UUID } from "crypto"
import { PartyVideoGame } from "./partyVideoGame"

export type Platform = {
  id: UUID
  name: string
  partyVideoGames: PartyVideoGame[]
}
