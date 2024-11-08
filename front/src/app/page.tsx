import Link from "next/link"

import routes from "@/routes"

export default function Home() {
  return (
    <div className="flex h-full justify-center items-center">
      <Link className="border-2 px-6 py-2 rounded-lg" href={routes.parties}>
        Parties list
      </Link>
    </div>
  )
}
