import Link from "next/link"

import routes from "@/routes"

export default function Home() {
  return (
    <div className="flex h-full justify-center items-center gap-4">
      <Link className="border-2 px-6 py-2 rounded-lg" href={routes.parties}>
        Parties list
      </Link>

      <Link className="border-2 px-6 py-2 rounded-lg" href={routes.addParty}>
        Add party
      </Link>
    </div>
  )
}
