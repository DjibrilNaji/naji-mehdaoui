import { Party } from "./party"
import { Platform } from "./platform"
import { VideoGame } from "./videoGame"

export type PartyVideoGame = {
  id: string
  party: Party
  videoGame: VideoGame
  platform: Platform
}
