"use client";

import { useState, ChangeEvent, FormEvent } from 'react';
import styles from "./page.module.css";
import { Tree } from './../tree/tree';

export default function Home() {
  const [tree, setTree] = useState<Tree>(new Tree());
  const [info, setInfo] = useState<string>('');
  const [node, setNode] = useState<string>('');
  const [parent, setParent] = useState<string>('');

  const updateNode = (e: ChangeEvent<HTMLInputElement>) => {
    setNode(e.target.value);
  };

  const updateParent = (e: ChangeEvent<HTMLInputElement>) => {
    setParent(e.target.value);
  };

  const handleSubmit = (e: FormEvent) => {
    e.preventDefault();

    tree.addText(node, parent, false)
    setInfo(tree.getTree);
  };

  return (
    <main className={styles.main}>
      <div>
        <form onSubmit={handleSubmit}>
          <div>
            Digite o nome do novo nível:
            <input type="text" value={node} onChange={updateNode} />
          </div>

          <div>
            Digite o nome do pai do nível (onde ele deve ficar):
            <input type="text" value={parent} onChange={updateParent} />
          </div>

          <button type="submit">Submit</button>
        </form>

        <pre>{info}</pre>
      </div>
    </main>
  );
}
